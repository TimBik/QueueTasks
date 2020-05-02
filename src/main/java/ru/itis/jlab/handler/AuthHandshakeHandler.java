package ru.itis.jlab.handler;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeFailureException;
import org.springframework.web.socket.server.HandshakeHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;
import org.springframework.web.util.WebUtils;
import ru.itis.jlab.model.Role;
import ru.itis.jlab.model.State;
import ru.itis.jlab.model.User;

import java.util.Map;
import java.util.Optional;

@Component
public class AuthHandshakeHandler implements HandshakeHandler {

    private DefaultHandshakeHandler defaultHandshakeHandler = new DefaultHandshakeHandler();

    @Override
    public boolean doHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws HandshakeFailureException {
        return defaultHandshakeHandler.doHandshake(serverHttpRequest, serverHttpResponse, webSocketHandler, map);
    }
}