package roomescape.service.reservation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import roomescape.domain.payment.Payment;
import roomescape.domain.reservation.ReservationStatus;
import roomescape.domain.reservationwaiting.ReservationWaitingWithRank;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ReservationMineResponse {
    private final Long reservationId;
    private final String theme;
    private final LocalDate date;
    private final LocalTime time;
    private final String status;
    private final String paymentKey;
    private final Integer amount;

    public ReservationMineResponse(Long reservationId, String theme, LocalDate date, LocalTime time, String status, String paymentKey, Integer amount) {
        this.reservationId = reservationId;
        this.theme = theme;
        this.date = date;
        this.time = time;
        this.status = status;
        this.paymentKey = paymentKey;
        this.amount = amount;
    }

    public ReservationMineResponse(Payment payment) {
        this(payment.getReservation().getId(),
                payment.getReservation().getTheme().getName().getName(),
                payment.getReservation().getDate(),
                payment.getReservation().getTime().getStartAt(),
                ReservationStatus.BOOKED.getDescription(),
                payment.getPaymentKey(),
                payment.getAmount()
        );
    }

    public ReservationMineResponse(ReservationWaitingWithRank waitingWithRank) {
        this(waitingWithRank.getWaiting().getReservation().getId(),
                waitingWithRank.getWaiting().getReservation().getTheme().getName().getName(),
                waitingWithRank.getWaiting().getReservation().getDate(),
                waitingWithRank.getWaiting().getReservation().getTime().getStartAt(),
                String.format(ReservationStatus.WAITING.getDescription(), waitingWithRank.getRank()),
                "",
                0
        );
    }

    public LocalDateTime retrieveDateTime() {
        return LocalDateTime.of(date, time);
    }

    public Long getReservationId() {
        return reservationId;
    }

    public String getTheme() {
        return theme;
    }

    public LocalDate getDate() {
        return date;
    }

    @JsonFormat(shape = Shape.STRING, pattern = "HH:mm")
    public LocalTime getTime() {
        return time;
    }

    public String getStatus() {
        return status;
    }

    public String getPaymentKey() {
        return paymentKey;
    }

    public Integer getAmount() {
        return amount;
    }
}
