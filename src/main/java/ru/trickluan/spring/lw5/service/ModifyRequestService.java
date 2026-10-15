package ru.trickluan.spring.lw5.service;

import org.springframework.stereotype.Service;
import ru.trickluan.spring.lw5.model.Request;

@Service
public interface ModifyRequestService {
    void modify(Request request);
}
