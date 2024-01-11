package com.rollingstone.events;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CategoryEventListener {

	@EventListener
	public void onApplicationEvent(CategoryEvent categoryEvent) {
		System.out.println("Recieve category event: "+categoryEvent.getEventType());
		System.out.println("Recieve category : "+categoryEvent.getCategory().toString());
	}
}
