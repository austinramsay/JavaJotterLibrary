package com.austinramsay.javajotterlibrary;

import java.io.Serializable;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.HashMap;
import java.net.URL;
import java.io.ObjectOutputStream;
import java.io.ByteArrayOutputStream;

public class Note implements Serializable {
    private Integer id;
	private Integer notebookId;
    private String title;
    private byte[] content;
	private HashMap<URL, String> imageAttachments;	// URL & Base64 Encoding

    /*public Note(Integer id, Integer notebookId, String title) {
        this.id = id;
		this.notebookId = notebookId;
        this.title = title;
    }*/

	public Note(Integer id, Integer notebookId, String title, byte[] content, HashMap<URL, String> imageAttachments) {
		this.id = id;
		this.notebookId = notebookId;
		this.title = title;
		this.content = content;
		this.imageAttachments = imageAttachments;
	}

	public void addImageAttachment(URL url, String base64Encoding) {
		imageAttachments.put(url, base64Encoding);
	}

	public String removeImageAttachment(URL url) {
		return imageAttachments.remove(url);
	}

	public HashMap<URL, String> getImageAttachments() {
		return imageAttachments;
	}

	public byte[] getImageAttachmentsBytes() {
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(getImageAttachments());
			oos.flush();
			return bos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

    @Override
    public String toString() {
        return title;
    }

    public Integer getId() {
        return id;
    }

	public Integer getNotebookId() {
		return notebookId;
	}

    public String getTitle() {
        return title;
    }

    public byte[] getContent() {
        return content;
    }

	public void setNotebookId(Integer notebookId) {
		this.notebookId = notebookId;
	}

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

	public int contentHashCode() {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(content);
			byte[] digest = md.digest();

			return ((digest[0] & 0xFF) << 24 |
					(digest[1] & 0xFF) << 16 |
					(digest[2] & 0xFF) << 8 |
					(digest[3] & 0xFF));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public int imageAttachmentsHashCode() {
		return getImageAttachments().hashCode();
	}

	@Override
	public int hashCode() {
		try {
			byte idByte = getId().byteValue();
			byte notebookIdByte = getNotebookId().byteValue();
			byte[] titleBytes = getTitle().getBytes("UTF-8");
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(getImageAttachments());
			byte[] imageAttachmentsBytes = bos.toByteArray();

			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(idByte);
			md.update(notebookIdByte);
			md.update(titleBytes);
			md.update(content);
			md.update(imageAttachmentsBytes);
			byte[] digest = md.digest();
			
			// MD5 digest returns 128 bits/16 bytes
			// Only extract a subset of the bytes and shift them into an int value
			// & 0xFF forces byte to be treated as unsigned value
			// Because, when working with bitwise operations in Java, it always
			// works on at least an 'int'-sized operand. So, the byte is converted
			// to an int during the process.
			// Ex. byte value -1 (binary 11111111) is promoted to an int:
			//  For a signed byte, Java uses sign extension to fill the higher bits
			//  during promotion, so -1 becomes 11111111111111111111111111111111 (32 bits)
			// Then, the & 0xFF operation removes all the leading 24 bits and only keeps
			// the original byte values.

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
		Note testNote = (Note) obj;
		return (
				(getId().equals(testNote.getId())) &&
				(getNotebookId().equals(testNote.getNotebookId())) &&
				(getTitle().equals(testNote.getTitle())) &&
				(Arrays.equals(getContent(), testNote.getContent())) &&
				(getImageAttachments().hashCode() == testNote.getImageAttachments().hashCode()));
	}
}