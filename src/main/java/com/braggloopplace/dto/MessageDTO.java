package com.braggloopplace.dto;

import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import java.time.Year;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class MessageDTO {

	private Integer messageId;

	private Text content;

	private Date sentDate;






}
