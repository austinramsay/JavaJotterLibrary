package com.austinramsay.javajotterlibrary;

import java.io.Serializable;

public class Transaction implements Serializable {
	Entity entity;
	TransactionType type;
	UpdateType updateField;
	Integer id;
	Object update;
	byte[] contentUpdate;

	public Transaction(Entity entity, TransactionType type, Integer id) {
		this.entity = entity;
		this.type = type;
		this.id = id;
	}

	public Transaction(Entity entity, TransactionType type, Integer id, UpdateType updateField, Object update) {
		this(entity, type, id);
		this.updateField = updateField;
		this.update = update;
	}

	public Transaction(Entity entity, TransactionType type, Integer id, UpdateType updateField, byte[] contentUpdate) {
		this(entity, type, id);
		this.updateField = updateField;
		this.contentUpdate = contentUpdate;
	}

	public Entity getEntity() {
		return entity;
	}

	public TransactionType getType() {
		return type;
	}

	public Integer getId() {
		return id;
	}

	public UpdateType getUpdateField() {
		return updateField;
	}

	public Object getUpdate() {
		return update;
	}

	public byte[] getContentUpdate() {
		return contentUpdate;
	}
}
