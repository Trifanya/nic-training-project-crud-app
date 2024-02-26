package dev.trifanya.server_app.activemq.handler;

import dev.trifanya.server_app.ServerApp;
import dev.trifanya.server_app.model.User;
import dev.trifanya.server_app.service.UserService;
import dev.trifanya.server_app.validator.UserValidator;
import dev.trifanya.server_app.activemq.producer.UserMessageProducer;

import org.apache.logging.log4j.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Map;
import java.util.HashMap;
import javax.jms.TextMessage;
import javax.jms.Destination;
import javax.jms.JMSException;

public class UserMessageHandler {
    private final Logger logger = ServerApp.logger;
    private final UserService userService;
    private final ObjectMapper objectMapper;
    private final UserValidator userValidator;
    private final UserMessageProducer userMessageProducer;

    public UserMessageHandler() throws JMSException {
        this.userService = new UserService();
        this.objectMapper = new ObjectMapper();
        this.userValidator = new UserValidator();
        this.userMessageProducer = new UserMessageProducer();
    }

    public void handleSignIn(Destination responseDestination, TextMessage textMessage) {
        logger.trace("UserMessageHandler: Вызван метод handleSignIn().");
        try {
            String[] credentials = textMessage.getText().split(" ");
            String email = credentials[0];
            String password = credentials[1];
            User user = userService.getUserByEmail(email);
            if (user.getPassword().equals(password)) {
                userMessageProducer.sendAuthUser(responseDestination, user);
            } else {
                userMessageProducer.sendErrorMessage(responseDestination, "Вы ввели неверный пароль.");
            }
        } catch (JMSException exception) {
            logger.error("UserMessageHandler: Произошла ошибка при обработке сообщения в методе handleSignIn()");
        } catch (Exception exception) {
            logger.warn("UserMessageHandler: Произошла ошибка при попытке войти в аккаунт.");
            userMessageProducer.sendErrorMessage(responseDestination, exception.getMessage());
        }
    }

    public void handleGetSingleUser(Destination responseDestination, TextMessage textMessage) {
        logger.trace("UserMessageHandler: Вызван метод handleGetSingleUser().");
        try {
            int userId = objectMapper.readValue(textMessage.getText(), Integer.class);
            userMessageProducer.sendUser(responseDestination, userService.getUserById(userId));
        } catch (JMSException | JsonProcessingException exception) {
            logger.error("UserMessageHandler: Произошла ошибка при обработке сообщения в методе handleGetSingleUser()");
        }
    }

    public void handleGetUserList(Destination responseDestination, TextMessage textMessage) {
        logger.trace("UserMessageHandler: Вызван метод handleGetUserList().");
        try {
            Map<String, String> userRequestParams = objectMapper.readValue(textMessage.getText(),
                    new TypeReference<HashMap<String, String>>() {});
            String userSortBy = userRequestParams.remove("sortBy");
            String userSortDir = userRequestParams.remove("sortDir");
            userMessageProducer.sendUserList(
                    responseDestination,
                    userService.getUsers(userRequestParams, userSortBy, userSortDir));
        } catch (JMSException | JsonProcessingException exception) {
            logger.error("UserMessageHandler: Произошла ошибка при обработке сообщения в методе handleGetUserList()");
        }
    }

    public void handleCreateUser(Destination responseDestination, TextMessage textMessage) {
        logger.trace("UserMessageHandler: Вызван метод handleCreateUser().");
        try {
            User userToSave = objectMapper.readValue(textMessage.getText(), User.class);
            userValidator.validateUser(userToSave);
            userService.createNewUser(userToSave);
            userMessageProducer.sendUserCreatedMessage(responseDestination, "Пользователь успешно зарегистрирован.");
        } catch (JMSException | JsonProcessingException exception) {
            logger.error("UserMessageHandler: Произошла ошибка при обработке сообщения в методе handleCreateUser()");
        } catch (Exception exception) {
            logger.warn("UserMessageHandler: Произошла ошибка при попытке создать пользвателя.");
            userMessageProducer.sendErrorMessage(responseDestination, exception.getMessage());
        }
    }

    public void handleUpdateUser(Destination responseDestination, TextMessage textMessage) {
        logger.trace("UserMessageHandler: Вызван метод handleUpdateUser().");
        try {
            User updatedUser = objectMapper.readValue(textMessage.getText(), User.class);
            userValidator.validateUser(updatedUser);
            userService.updateUserInfo(updatedUser);
            userMessageProducer.sendUserCreatedMessage(responseDestination, "Профиль успешно отредактирован.");
        } catch (JMSException | JsonProcessingException exception) {
            logger.error("UserMessageHandler: Произошла ошибка при обработке сообщения в методе handleUpdateUser()");
        } catch (Exception exception) {
            logger.warn("UserMessageHandler: Произошла ошибка при попытке обновить данные пользователя.");
            userMessageProducer.sendErrorMessage(responseDestination, exception.getMessage());
        }
    }

    public void handleDeleteUser(Destination responseDestination, TextMessage textMessage) {
        logger.trace("UserMessageHandler: Вызван метод handleDeleteUser().");
        try {
            int userToDeleteId = objectMapper.readValue(textMessage.getText(), Integer.class);
            userService.deleteUserById(userToDeleteId);
            userMessageProducer.sendUserCreatedMessage(responseDestination, "Пользователь успешно удален.");
        } catch (JMSException | JsonProcessingException exception) {
            logger.error("UserMessageHandler: Произошла ошибка при обработке сообщения в методе handleDeleteUser()");
        } catch (Exception exception) {
            logger.warn("UserMessageHandler: Произошла ошибка при попытке удалить пользователя.");
            userMessageProducer.sendErrorMessage(responseDestination, exception.getMessage());
        }
    }
}
