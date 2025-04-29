package com.austinramsay.javajotterlibrary;

import java.io.Serializable;
import java.security.MessageDigest;

public class Notebook implements Serializable {
    private final Integer id;
	private Integer parentId;
    private String title;

    public Notebook(Integer id, String title) {
        this.id = id;
		this.parentId = null;
        this.title = title;
    }

	public Notebook(Integer id, Integer parentId, String title) {
		this.id = id;
		this.parentId = parentId;
		this.title = title;
	}

    @Override
    public String toString() {
        return title;
    }

    public Integer getId() {
        return id;
    }

	public Integer getParentId() {
		return parentId;
	}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

	public void setParentId(Integer newParentId) {
		parentId = newParentId;
	}

	@Override
	public int hashCode() {
		try {
			byte idByte = getId().byteValue();
			byte parentIdByte = (getParentId() == null) ? 0 : getParentId().byteValue();
			byte[] titleBytes = getTitle().getBytes("UTF-8");

			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(idByte);
			md.update(parentIdByte);
			md.update(titleBytes);
			byte[] digest = md.digest();

			return ((digest[0] & 0xFF) << 24) |
					((digest[1] & 0xFF) << 16) |
					((digest[2] & 0xFF << 8)) |
					(digest[3] & 0xFF);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Notebook testNotebook = (Notebook) obj;
		return (
				(getId().equals(testNotebook.getId()) &&
				(getParentId().equals(testNotebook.getParentId())) &&
				(getTitle().equals(testNotebook.getTitle()))
				));
	}
}