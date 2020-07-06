package io.github.anantharajuc.sbtest.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.anantharajuc.sbtest.model.ApplicationSetings;
import io.github.anantharajuc.sbtest.repository.ApplicationSettingsRepository;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Getter
@Setter
@Service
public class OtherServicesImpl implements OtherServices
{
	//Application Settings
	private String applicationName;
	private String applicationVersion;
	private String postmanEchoGETurl;
	
	@Autowired
	private ApplicationSettingsRepository applicationSettingsRepository;
		
	@Override
	public void loadApplicationSettings() 
	{
		log.info("-----> Loading Application Settings Value");
		
		List<ApplicationSetings> applicationSettingsList = applicationSettingsRepository.findAll();
		
		HashMap<String, String> applicationSettingsHashMap = new HashMap<>(); 
		
		for(int i = 0; i< applicationSettingsList.size(); i++)
		{
			applicationSettingsHashMap.put(applicationSettingsList.get(i).getAppKey(), applicationSettingsList.get(i).getAppValue());
		}
		
		setApplicationName(applicationSettingsHashMap.get("applicationName"));
		setApplicationVersion(applicationSettingsHashMap.get("applicationVersion"));
		setPostmanEchoGETurl(applicationSettingsHashMap.get("postmanEchoGETurl"));
	}
}