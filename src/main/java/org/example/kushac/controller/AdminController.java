package org.example.kushac.controller;

import lombok.RequiredArgsConstructor;
import org.example.kushac.service.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final ReservationService reservationService;

    @GetMapping("/reservations")
    public String allReservations(Model model) {
        model.addAttribute("reservations", reservationService.findAll());
        return "admin_panel";
    }

    @PostMapping("/reservations/{id}/status")
    public String updateStatus(@PathVariable Long id, @RequestParam String status) {
        reservationService.updateStatus(id, status);
        return "redirect:/admin/reservations";
    }
}