package com.bloodbank.backend.controllers;
import com.bloodbank.backend.models.Event;
import com.bloodbank.backend.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("api/events")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @GetMapping
    public List<Event> getAllEvents(){
        return  eventRepository.findAll();
    }

    @GetMapping("/{id}")
    public Event getOne(@PathVariable Long id){
        Optional<Event> event = eventRepository.findById(id);
        return event.get();
    }

    @PostMapping
    public Event createEvent(@RequestBody Event newEvent){
        Event event=eventRepository.save(newEvent);
        return event;

    }

    @DeleteMapping
    public void deleteAll(){
        eventRepository.deleteAll();
    }



    public String deleteOne(@PathVariable Long id){
        Optional<Event> found=eventRepository.findById(id);
        if(found.isEmpty()) return  "event does not exist in our system";
        eventRepository.deleteById(id);
        return  "event deleted successfully";
    }


}
