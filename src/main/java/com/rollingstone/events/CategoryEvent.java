package com.rollingstone.events;

import org.springframework.context.ApplicationEvent;

import com.rollingstone.models.Category;

@SuppressWarnings("serial")
public class CategoryEvent extends ApplicationEvent {

	private String eventType;
	private Category category;

	public CategoryEvent(String eventType, Category category) {
		super(category);
		this.eventType = eventType;
		this.category = category;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "CategoryEvent [eventType=" + eventType + ", category=" + category + "]";
	}

}
