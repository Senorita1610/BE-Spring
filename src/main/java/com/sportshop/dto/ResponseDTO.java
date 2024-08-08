package com.sportshop.dto;

import java.io.Serializable;

import com.sportshop.utils.MsgUtil;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseDTO<T> implements Serializable {

	private boolean success;
	private Data<T> data;

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class Data<D> {
		String msgContent;
		String msgCode;
		D payload;
	}

	public static <T> ResponseDTO<T> ok(String message) {
		return msgExtract(
				ResponseDTO.<T>builder().success(true).data(Data.<T>builder().msgContent(message).build()).build());
	}

	public static <T> ResponseDTO<T> ok(String message, T data) {
		return msgExtract(ResponseDTO.<T>builder().success(true)
				.data(Data.<T>builder().msgContent(message).payload(data).build()).build());
	}

	public static <T> ResponseDTO<T> ok(T data) {
		return ResponseDTO.<T>builder().success(true).data(Data.<T>builder().payload(data).build()).build();
	}

	public static <T> ResponseDTO<T> err(String msg) {
		return msgExtract(
				ResponseDTO.<T>builder().success(false).data(Data.<T>builder().msgContent(msg).build()).build());
	}

	public static <T> ResponseDTO<T> err(String key, String msg) {
		return msgExtract(ResponseDTO.<T>builder().success(false)
				.data(Data.<T>builder().msgCode(key).msgContent(msg).payload(null).build()).build());
	}

	public static <T> ResponseDTO<T> errBadRequest(String msg) {
		return msgExtract(ResponseDTO.<T>builder().success(false)
				.data(Data.<T>builder().msgCode(msg).msgContent(MsgUtil.getMessage(msg)).payload(null).build())
				.build());
	}

	private static <T> ResponseDTO<T> msgExtract(ResponseDTO<T> response) {
		String[] extract = response.data.msgContent != null ? response.data.msgContent.split(MsgUtil.SPLIT) : null;
		if (null != extract && extract.length == 2) {
			response.data.msgCode = extract[0];
			response.data.msgContent = extract[1];
		} else if (extract != null && response.getData().getPayload() == null) {
			response.data.payload = (T) response.data.msgContent;
			response.data.msgContent = "";
		}
		return response;
	}

}