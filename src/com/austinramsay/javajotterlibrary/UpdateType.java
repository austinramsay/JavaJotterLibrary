package com.austinramsay.javajotterlibrary;

import java.io.Serializable;

public enum UpdateType implements Serializable {
	TITLE,
	MAP_NOTEBOOK_TO_PARENT_NOTEBOOK_ID,
	MAP_NOTE_TO_NOTEBOOK_ID,
	CONTENT,
	IMAGE_ATTACHMENTS
}