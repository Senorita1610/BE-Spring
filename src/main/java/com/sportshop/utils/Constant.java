package com.sportshop.utils;

import org.springframework.http.HttpStatus;

public interface Constant {

	enum STATUS {
		ACTIVE(1), INACTIVE(0);

		final int status;

		public int value() {
			return status;
		}

		STATUS(int status) {
			this.status = status;
		}
	}

	enum ROLE {
		USER("USER"), ADMIN("ADMIN");

		final String role;

		public String value() {
			return role;
		}

		ROLE(String role) {
			this.role = role;
		}
	}

	enum MONTH_EVEN {
		APRIL(4), JUNE(6), SEPTEMBER(9), NOVEMBER(11);

		public int value() {
			return value;
		}

		final int value;

		MONTH_EVEN(int value) {
			this.value = value;
		}
	}

	interface Sequence {
		String SEQ_USERS = "SEQ_USER_ID";
		String SEQ_USER_INFO = "SEQ_USER_INFO_ID";
		String SEQ_BOOK = "SEQ_BOOK_ID";
		String SEQ_CATEGORY = "SEQ_CATEGORY_ID";
		String SEQ_COMMENT = "SEQ_COMMENT_ID";
		String SEQ_BILL = "SEQ_BILL_ID";
		String SEQ_TEST = "SEQ_TEST";
	}

	interface Regex {
		String PHONE_NUMBER = "((\\+84|0)(3|5|7|8|9))+([0-9]{8})";
		String EMAIL = "^[a-zA-Z0-9-_]+(?:\\.[a-zA-Z0-9]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
	}

	Integer HTTP_ERROR_STATUS = HttpStatus.INTERNAL_SERVER_ERROR.value();
	Integer PAGE_INDEX_DEFAULT = 0;
	Integer PAGE_SIZE_DEFAULT = 10;
	String ROOT_PATH = "home/";
	String AVATAR_PATH = "avatar/";
	String BOOK_PATH = "book/";

	String USERS_EXPORT_FILE_NAME = "Danh_sach_users_%s";
	String USERS_IMPORT_TEMP_FILE_NAME = "Template_import_users";
	String DEFAULT_PASSWORD = "123456";
	String HASH_SEED = "c^t!sBdqsDds@]Gasd$ksdf.A$38.[sa<ji~ask#%^#&";

	String KEY_TINY = "tsgryrXf4cc1W92ww5mLMSyDSzTRDnh3";
	int MAX_SIZE_ECM = 512; // KB

	int MAX_DAY = 31;
	int MAX_DAY_FEB = 29;
	int START_DAY = 0;
	int END_DAY = 2;
	int START_MONTH = 3;
	int END_MONTH = 5;
}