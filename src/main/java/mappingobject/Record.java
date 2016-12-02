package mappingobject;

public class Record {

	private String recordId;
	private String Name;
	private String DateCreated;
	private String DueDate;
	private String DateUpdated;
	private String AudioLength;
	private String Content;

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getDateCreated() {
		return DateCreated;
	}

	public void setDateCreated(String dateCreated) {
		DateCreated = dateCreated;
	}

	public String getDueDate() {
		return DueDate;
	}

	public void setDueDate(String dueDate) {
		DueDate = dueDate;
	}

	public String getDateUpdated() {
		return DateUpdated;
	}

	public void setDateUpdated(String dateUpdated) {
		DateUpdated = dateUpdated;
	}

	public String getAudioLength() {
		return AudioLength;
	}

	public void setAudioLength(String audioLength) {
		AudioLength = audioLength;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

}
