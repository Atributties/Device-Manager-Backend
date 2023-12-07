package com.example.devicemanagerbackend.services;


import com.example.devicemanagerbackend.DTO.MessageDTO;
import com.example.devicemanagerbackend.DTO.UserRequestDTO;
import com.example.devicemanagerbackend.entities.Message;
import com.example.devicemanagerbackend.entities.User;
import com.example.devicemanagerbackend.entities.UserRequest;
import com.example.devicemanagerbackend.exceptions.CustomException;
import com.example.devicemanagerbackend.repositories.MessageRepository;
import com.example.devicemanagerbackend.repositories.UserRepository;
import com.example.devicemanagerbackend.repositories.UserRequestRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChatService {


    private final UserRequestRepository userRequestRepository;
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    @Autowired
    public ChatService(UserRequestRepository userRequestRepository, MessageRepository messageRepository, UserRepository userRepository) {
        this.userRequestRepository = userRequestRepository;
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    public UserRequestDTO createUserRequest(UserRequestDTO userRequestDTO) {
        // Convert UserRequestDTO to UserRequest entity
        UserRequest userRequest = convertToEntity(userRequestDTO);
        // Save the user request in the database
        UserRequest createdUserRequest = userRequestRepository.save(userRequest);
        // Convert the created UserRequest back to UserRequestDTO
        return convertUserRequestToDTO(createdUserRequest);
    }


    public Optional<List<UserRequestDTO>> getAllUserRequestsWithMessages() {
        List<UserRequest> userRequests = userRequestRepository.findAll();
        if (!userRequests.isEmpty()) {
            return Optional.of(userRequests.stream()
                    .map(this::convertUserRequestToDTO)
                    .collect(Collectors.toList()));
        } else {
            return Optional.empty();
        }
    }

    public UserRequestDTO getUserRequest(int requestId) {
        return userRequestRepository.findById(requestId)
                .map(this::convertUserRequestToDTO)
                .orElseThrow(() -> new CustomException("User request not found with ID: " + requestId));
    }

    public List<UserRequestDTO> getAllUserRequestsForUser(int userId) {
        List<UserRequest> userRequests = userRequestRepository.findAllByUserId(userId);
        return userRequests.stream()
                .map(this::convertUserRequestToDTO)
                .collect(Collectors.toList());
    }



    @Transactional
    public Optional<UserRequest> addMessageToUserRequest(int requestId, MessageDTO messageDTO) {
        // Hent den eksisterende UserRequest fra databasen
        UserRequest userRequest = userRequestRepository.findById(requestId)
                .orElseThrow(() -> new EntityNotFoundException("UserRequest not found with id: " + requestId));

        // Opret Message direkte fra MessageDTO
        Message message = new Message();
        message.setId(messageDTO.getId());
        message.setUserRequest(userRequest);
        message.setUser(messageDTO.getUser());
        message.setMessageText(messageDTO.getMessageText());
        message.setDateCreated(messageDTO.getDateCreated());

        // Gem den oprettede Message i databasen
        Message savedMessage = messageRepository.save(message);

        // Opdater UserRequest med den nye besked
        userRequest.getMessages().add(savedMessage);

        // Gem opdateret UserRequest i databasen
        userRequestRepository.save(userRequest);
        return Optional.of(userRequest);
    }

    //Converters

    private UserRequestDTO convertUserRequestToDTO(UserRequest userRequest) {
        List<MessageDTO> messageDTOs = userRequest.getMessages().stream()
                .map(this::convertMessagesToDTO)
                .collect(Collectors.toList());

        return new UserRequestDTO(
                userRequest.getId(),
                userRequest.getRequestType(),
                userRequest.getUser(),
                userRequest.getRequestText(),
                messageDTOs,
                userRequest.getDateCreated(),
                userRequest.getLastUpdated()
        );
    }

    private MessageDTO convertMessagesToDTO(Message message) {
        // Convert each Message entity to MessageDTO
        return new MessageDTO(
                message.getId(),
                message.getUserRequest(),
                message.getUser(),
                message.getMessageText(),
                message.isReaded(),
                message.getDateCreated()

        );
    }

    private UserRequest convertToEntity(UserRequestDTO userRequestDTO) {
        // Fetch the existing User from the database
        User user = userRepository.findById(userRequestDTO.getUser().getId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userRequestDTO.getUser().getId()));

        List<Message> messages = Optional.ofNullable(userRequestDTO.getMessages())
                .orElse(Collections.emptyList())
                .stream()
                .map(this::convertMessagesDTOToEntities)
                .collect(Collectors.toList());

        // Create a new UserRequest with the managed User entity
        return new UserRequest(
                userRequestDTO.getId(),
                userRequestDTO.getRequestType(),
                user,
                userRequestDTO.getRequestText(),
                messages,
                userRequestDTO.getDateCreated(),
                userRequestDTO.getLastUpdated()
        );
    }

    private Message convertMessagesDTOToEntities(MessageDTO messageDTO) {
        if (messageDTO == null) {
            return null; // Or handle as needed if null messages are not allowed
        }
        return new Message(
                messageDTO.getId(),
                messageDTO.getUserRequest(),
                messageDTO.getUser(),
                messageDTO.getMessageText(),
                messageDTO.isReaded(),
                messageDTO.getDateCreated()
        );
    }














}
