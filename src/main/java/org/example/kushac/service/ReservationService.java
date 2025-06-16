package org.example.kushac.service;

import lombok.RequiredArgsConstructor;
import org.example.kushac.dto.ReservationRequest;
import org.example.kushac.entity.Reservation;
import org.example.kushac.entity.UserEntity;
import org.example.kushac.repository.ReservationRepository;
import org.example.kushac.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    private final UserRepository userRepository;

    public Reservation createReservation(String userId, ReservationRequest request) {
        UserEntity user = userRepository.findByUsername(userId).orElseThrow();

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setReservationDate(request.getReservationDate());
        reservation.setReservationTime(request.getReservationTime());
        reservation.setGuestsCount(request.getGuestsCount());
        reservation.setContactPhone(request.getContactPhone());

        return reservationRepository.save(reservation);
    }

    public List<Reservation> findByUsername(String username) {
        return reservationRepository.findByUserUsername(username);
    }

    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    public void updateStatus(Long id, String status) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Бронирование не найдено"));

        reservation.setStatus(status);
        reservationRepository.save(reservation);
    }

}