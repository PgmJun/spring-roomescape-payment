package roomescape.service.reservation.dto;

import java.time.DateTimeException;
import java.time.LocalDate;
import roomescape.exception.common.InvalidRequestBodyException;

public class AdminReservationRequest {
    private final LocalDate date;
    private final Long timeId;
    private final Long themeId;
    private final Long memberId;

    public AdminReservationRequest(String date, String timeId, String themeId, String memberId) {
        validate(date, timeId, themeId, memberId);
        this.date = LocalDate.parse(date);
        this.timeId = Long.parseLong(timeId);
        this.themeId = Long.parseLong(themeId);
        this.memberId = Long.parseLong(memberId);
    }

    public void validate(String date, String timeId, String themeId, String memberId) {
        if (date == null || timeId == null || themeId == null || memberId == null) {
            throw new InvalidRequestBodyException();
        }
        try {
            LocalDate.parse(date);
        } catch (DateTimeException e) {
            throw new InvalidRequestBodyException();
        }
    }

    public ReservationSaveInput toReservationSaveInput() {
        return new ReservationSaveInput(date, timeId, themeId);
    }

    public LocalDate getDate() {
        return date;
    }

    public Long getTimeId() {
        return timeId;
    }

    public Long getThemeId() {
        return themeId;
    }

    public Long getMemberId() {
        return memberId;
    }
}
