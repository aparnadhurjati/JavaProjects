package com.aparna.java.hello_springai;

import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aparna.java.hello_springai.HelloSpringAIController.Career;

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
}
