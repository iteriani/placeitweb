package com.classproj.placeit;

import java.util.List;

import Models.PlaceIt;

public interface iView {
	
	public void addMarker(PlaceIt pc);
	
	public void removeMarker(PlaceIt pc);
	
	public void notifyUser(List<PlaceIt> pc);

	
}
