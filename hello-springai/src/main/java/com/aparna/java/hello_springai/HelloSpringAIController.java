package com.aparna.java.hello_springai;

import java.util.List;
import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



import reactor.core.publisher.Flux;

@RestController
public class HelloSpringAIController {
    /**
     * Basic endpoint to check if the server is running
     * @return
     */
    @GetMapping("/")
    public String hello() {
        return "Hello, World!";
    }

    private final ChatClient chatClient;
 
    public HelloSpringAIController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }
 
    /**
     * Basic prompt to the model
     * @param message
     * @return
     */
    @GetMapping("/basic")
    String basic(@RequestParam String message) {
         ChatResponse chatResponse = this.chatClient.prompt()
                    .user(message)
                    .call()
                    .chatResponse();
        return chatResponse.toString();
    }

    /**
     * Uses system prompt to provide context to the model.
     * @return
     * Sample response:
     * "Jerry Seinfeld is a fictional character from the TV show Seinfeld. He is a stand-up comedian and actor who is known for his catchphrases and his relationship with George Costanza."
     */
    @GetMapping("/system")
    String system() {
    return this.chatClient.prompt()
            .system("You are a chat bot who uses quotes from the TV show Seinfeld.") //provides context to the model
            .user("Who is Jerry Seinfeld?")
            .call()
            .content();

    }
    /**
     * Streams the response from the model
     * @param message
     * @return
     */
    @GetMapping("/stream")
    Flux<String> stream(@RequestParam String message) {
        return this.chatClient.prompt()
                .user(message)
                .stream()
                .content();
    }
    /**
     * 
     * @return
     * Sample response:
     * CareerList[careers=[Career[careerName=Surgeon, description=Medical professionals who perform surgeries., salary=409000], Career[careerName=Anesthesiologist, description=Physicians who administer anesthesia during surgeries., salary=386000], Career[careerName=Oral and Maxillofacial Surgeon, description=Specialists in surgery of the mouth and face., salary=348000], Career[careerName=Obstetrician/Gynecologist, description=Doctors specializing in women's reproductive health., salary=308000], Career[careerName=Orthodontist, description=Dentists specialized in correcting teeth and jaws., salary=304000], Career[careerName=Psychiatrist, description=Medical doctors who specialize in mental health., salary=220000], Career[careerName=Corporate Executive (CEO), description=Top executives responsible for overall operations., salary=197000], Career[careerName=Data Scientist, description=Professionals who analyze and interpret complex data., salary=150000], Career[careerName=Pharmacist, description=Healthcare professionals who dispense medications., salary=128000], Career[careerName=IT Manager, description=Professionals overseeing IT departments and projects., salary=120000]]]
     */
    @GetMapping("/career")
    String career() {
         CareerList careerList = this.chatClient.prompt()
                        .user("What are the top 10 careers by salary in the world?")
                        .call()
                        .entity(CareerList.class);
        return careerList.toString();
    }

    public record CareerList(List<Career> careers) {}
    public record Career(String careerName, String description, Long salary) {}

    /**
     * Uses a prompt template to generate a prompt.
     * @param name
     * @return
     */
    @GetMapping("/promptwhois")
    String promptWhoIs(@RequestParam String name) {
 
    PromptTemplate promptTemplate = new PromptTemplate("Who is {name}");
    Prompt prompt = promptTemplate.create(Map.of("name", name));
 
    return this.chatClient.prompt(prompt)
            .call()
            .content();
    }

    /**
     * Uses a prompt template to generate a prompt.
     * @param movie
     * @return
     */
    @GetMapping("/promptmessages")
    String promptMessages(@RequestParam String movie) {
    
        Message userMessage = new UserMessage("Telll me a joke");
    
        SystemPromptTemplate systemPromptTemplate = new SystemPromptTemplate("You are a chat bot who uses quotes of {movie} when responding.");
        Message systemMessage = systemPromptTemplate.createMessage(Map.of("movie", movie));
    
        Prompt prompt = new Prompt(List.of(systemMessage, userMessage));
    
        return this.chatClient.prompt(prompt)
                .call()
                .content();
    }




    ChatMemory chatMemory = MessageWindowChatMemory.builder()
    .maxMessages(2)
    .build();
    String conversationId = "007";

    /**
     * Uses a chat memory to store the conversation.
     * @param message
     * @return
     * we have only 2 messages in the memory.
     * Sample response with request message = "my name is James Bond" when memory is empty:Nice to meet you, James Bond! How can I assist you today?
     * Sample response with request message = "what is my name" and when memory is not empty:Your name is James Bond. How can I help you today?
     * Sample response with request message ="what is my name" when memory is empty:I'm sorry, but I don't have access to personal information about individuals unless you've shared it with me in this conversation. If you'd like to tell me your name or if you have any other questions, feel free to share!
     *
     */
    @GetMapping("/chatmemory")
    String chatMemory(@RequestParam String message) {
        
        return this.chatClient.prompt()
                .advisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, conversationId))
                .user(message)
                .call()
                .content();
    }

}
