package kz.iitu.remont.service;

import org.springframework.stereotype.Service;
import org.springframework.web.HttpRequestMethodNotSupportedException;

@Service
public class ExceptionService {

    public int testException() {
        throw new NullPointerException("Some exception");
    }

}
