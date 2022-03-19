package com.peng.note.conf;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Author : code
 * @Date 2022/2/26 12:40
 * @Version 1.0
 */
@Configuration
@Data
public class SystemCOnf {

    @Value("toen.secret")
    private String TOKEN_SECRET;
}
