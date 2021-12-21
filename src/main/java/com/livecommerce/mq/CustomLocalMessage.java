package com.livecommerce.mq;

import com.livecommerce.local.dto.RequestLocal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomLocalMessage {

    private String messageId;
    private RequestLocal message;
    private Date messageDate;

}