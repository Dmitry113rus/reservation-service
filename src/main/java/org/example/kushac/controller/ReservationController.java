package org.example.kushac.controller;

import lombok.RequiredArgsConstructor;
import org.example.kushac.dto.ReservationRequest;
import org.example.kushac.entity.Reservation;
import org.example.kushac.service.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping
    public String getUserReservations(Model model, Principal principal) {
        // Получаем имя пользователя
        String username = principal.getName();

        // Загружаем бронирования текущего пользователя
        List<Reservation> reservations = reservationService.findByUsername(username);
        model.addAttribute("reservations", reservations);

        return "my_reservations"; // имя Thymeleaf-шаблона
    }

    @GetMapping("/create")
    public String showReservationForm(Model model) {
        model.addAttribute("reservation", new ReservationRequest());
        return "reservation_form";
    }

    @PostMapping("/create")
    public String createReservation(@ModelAttribute("reservation") ReservationRequest request,
                                    BindingResult result, Principal principal) {
        if (result.hasErrors()) {
            return "reservation_form";
        }

        reservationService.createReservation(principal.getName(), request);
        return "redirect:/reservations";
    }
}