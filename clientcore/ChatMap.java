package hello.clientcore;

import hello.test.ClientTest;

import java.util.HashMap;
import java.util.Map;

public class ChatMap {
	private static Map<String , ClientTest> chatMap = new HashMap<String , ClientTest> ();
	

	public static Map getChatMap() {
		return chatMap;
	}

	public static void setChatMap(Map chatMap) {
		ChatMap.chatMap = chatMap;
	}
	public static ClientTest getChatMap(String id){

		return chatMap.get(id);
	}
	public static void addChatMap(String id, ClientTest ClientTest){
		chatMap.put(id,ClientTest);
	}
}
