package com.example.challenge1.dev2qaExample_Custom_List;

/**
 * Created by Jerry on 1/21/2018.
 */

public class ListViewItemDTO {

	private boolean checked = false;

	private String itemText = "";

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getItemText() {
		return itemText;
	}

	public void setItemText(String itemText) {
		this.itemText = itemText;
	}
}
