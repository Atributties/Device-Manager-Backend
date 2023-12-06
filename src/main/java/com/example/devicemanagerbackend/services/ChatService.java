package com.example.devicemanagerbackend.services;


import com.example.devicemanagerbackend.DTO.MessageDTO;
import com.example.devicemanagerbackend.DTO.UserRequestDTO;
import com.example.devicemanagerbackend.entities.Message;
import com.example.devicemanagerbackend.entities.UserRequest;
import com.example.devicemanagerbackend.repositories.MessageRepository;
import com.example.devicemanagerbackend.repositories.UserRequestRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatService {


    private final UserRequestRepository userRequestRepository; // Erstat UserRequestRepository med den faktiske repositoryklasse
    private final MessageRepository messageRepository;

    @Autowired
    public ChatService(UserRequestRepository userRequestRepository, MessageRepository messageRepository) {
        this.userRequestRepository = userRequestRepository;
        this.messageRepository = messageRepository;
    }

    public UserRequestDTO getUserRequestWithMessages(int requestId) {
        UserRequest userRequest = userRequestRepository.findById(requestId)
                .orElseThrow(() -> new EntityNotFoundException("UserRequest not found with id: " + requestId));

        return convertToUserRequestDTOWithMessages(userRequest);
    }

    public List<UserRequestDTO> getAllUserRequestsForUser(int userId) {
        List<UserRequest> userRequests = userRequestRepository.findByUserId(userId);
        return userRequests.stream()
                .map(this::convertToUserRequestDTOWithMessages)
                .collect(Collectors.toList());
    }

    public UserRequestDTO getUserRequestWithMessagesForUser(int userId, int requestId) {
        UserRequest userRequest = userRequestRepository.findByIdAndUserId(requestId,  userId)
                .orElseThrow(() -> new EntityNotFoundException("UserRequest not found with id: " + requestId + " for user id: " + userId));

        return convertToUserRequestDTOWithMessages(userRequest);
    }


    public List<UserRequestDTO> getAllUserRequestsWithMessages() {
        List<UserRequest> allUserRequests = userRequestRepository.findAll();
        return allUserRequests.stream()
                .map(this::convertToUserRequestDTOWithMessages)
                .collect(Collectors.toList());
    }

    private UserRequestDTO convertToUserRequestDTOWithMessages(UserRequest userRequest) {
        UserRequestDTO userRequestDTO = convertToDTO(userRequest);
        userRequestDTO.setMessages(convertToMessageDTOList(userRequest.getMessages()));
        return userRequestDTO;
    }

    private List<MessageDTO> convertToMessageDTOList(List<Message> messages) {
        return messages.stream()
                .map(this::convertToMessageDTO)
                .collect(Collectors.toList());
    }



    public UserRequestDTO createUserRequest(UserRequestDTO userRequestDTO) {
        // Konverter UserRequestDTO til entiteten UserRequest
        UserRequest userRequest = convertToEntity(userRequestDTO);
        // Gem den oprettede UserRequest i databasen
        UserRequest savedUserRequest = userRequestRepository.save(userRequest);
        // Konverter den gemte UserRequest til UserRequestDTO og returner den
        return convertToDTO(savedUserRequest);
    }

    @Transactional
    public UserRequestDTO addMessageToUserRequest(int requestId, MessageDTO messageDTO) {
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
        UserRequest savedUserRequest = userRequestRepository.save(userRequest);

        // Opret UserRequestDTO direkte fra savedUserRequest
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setId(savedUserRequest.getId());
        userRequestDTO.setRequestType(savedUserRequest.getRequestType());
        userRequestDTO.setUser(savedUserRequest.getUser());
        userRequestDTO.setRequestText(savedUserRequest.getRequestText());
        userRequestDTO.setMessages(savedUserRequest.getMessages().stream()
                .map(this::convertToMessageDTO)
                .collect(Collectors.toList()));
        userRequestDTO.setDateCreated(savedUserRequest.getDateCreated());
        userRequestDTO.setLastUpdated(savedUserRequest.getLastUpdated());

        return userRequestDTO;
    }

    // Konverter Message til MessageDTO
    private MessageDTO convertToMessageDTO(Message message) {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setId(message.getId());
        messageDTO.setUserRequestId(message.getUserRequest());
        messageDTO.setUser(message.getUser());
        messageDTO.setMessageText(message.getMessageText());
        messageDTO.setDateCreated(message.getDateCreated());
        return messageDTO;
    }







    // Konverter UserRequestDTO til entiteten UserRequest
    private UserRequest convertToEntity(UserRequestDTO userRequestDTO) {
        UserRequest userRequest = new UserRequest();
        userRequest.setRequestType(userRequestDTO.getRequestType());
        userRequest.setUser(userRequestDTO.getUser());
        userRequest.setRequestText(userRequestDTO.getRequestText());
        return userRequest;
    }

    // Konverter UserRequest til UserRequestDTO
    private UserRequestDTO convertToDTO(UserRequest userRequest) {
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setId(userRequest.getId());
        userRequestDTO.setRequestType(userRequest.getRequestType());
        userRequestDTO.setUser(userRequest.getUser());
        userRequestDTO.setRequestText(userRequest.getRequestText());
        return userRequestDTO;
    }


}
