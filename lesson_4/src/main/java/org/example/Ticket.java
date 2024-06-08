package org.example;

import java.time.Instant;
import java.util.Optional;

public class Ticket {
    private final String id;
    private final long creationTime;
    private TicketPrice price;
    private String concertHall;
    private Integer eventCode;
    private Long time;
    private Boolean isPromo;
    private StadiumSector sector;
    private Double allowedBackpackWeight;

    private Ticket() {
        this.id = generateId();
        this.creationTime = getCurrentUnixTime();
    }

    private Ticket(TicketPrice price, String concertHall, Integer eventCode, Long time) {
        this();
        this.price = price;
        this.concertHall = concertHall.trim();
        this.eventCode = eventCode;
        this.time = time;
    }

    private Ticket(
            TicketPrice price,
            String concertHall,
            Integer eventCode,
            Long time,
            Boolean isPromo,
            StadiumSector sector,
            Double allowedBackpackWeight) {
        this(price, concertHall, eventCode, time);
        this.isPromo = isPromo;
        this.sector = sector;
        this.allowedBackpackWeight = allowedBackpackWeight;
    }

    public static Ticket createEmpty() {
        return new Ticket();
    }

    public static Ticket createLimited(TicketPrice price, String concertHall, Integer eventCode, Long time) {

        validateConcertHall(concertHall);
        validateEventCode(eventCode);

        return new Ticket(price, concertHall, eventCode, time);
    }

    public static Ticket create(
            TicketPrice price,
            String concertHall,
            Integer eventCode,
            Long time,
            Boolean isPromo,
            StadiumSector sector,
            Double allowedBackpackWeight) {

        validateConcertHall(concertHall);
        validateEventCode(eventCode);
        validateWeight(allowedBackpackWeight);

        return new Ticket(price, concertHall, eventCode, time, isPromo, sector, allowedBackpackWeight);
    }

    public String getId() {
        return id;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public Optional<TicketPrice> getPrice() {
       return get(price);
    }

    public Optional<String> getConcertHall() {
       return get(concertHall);
    }

    public Optional<Integer> getEventCode() {
       return get(eventCode);
    }

    public Optional<Long> getTime() {
       return get(time);
    }

    public Optional<Boolean> isPromo() {
        return get(isPromo);
    }

    public Optional<StadiumSector> getSector() {
       return get(sector);
    }

    public Optional<Double> getAllowedBackpackWeight() {
        return get(allowedBackpackWeight);
    }

    private <T>Optional<T> get(T value){
        return value == null ? Optional.empty() : Optional.of(value);
    }

    private String generateId() {
        String digits = String.valueOf(1 + (int) (Math.random() * ((999 - 1) + 1)));
        Character character = (char)(65 + (int) (Math.random() * ((90 - 65) + 1)));
        return character + digits;
    }

    private long getCurrentUnixTime() {
        return Instant.now().getEpochSecond();
    }

    private static void validateConcertHall(String concertHall) {
        if (concertHall == null || concertHall.trim().length() > 10) {
            throw new IllegalArgumentException("Concert hall must be specified and contain no more than 10 chars");
        }
    }

    private static void validateEventCode(Integer eventCode) {
        if (Integer.toString(eventCode).length() != 3) {
            throw new IllegalArgumentException("Event code must contain 3 digits");
        }
    }

    private static void validateWeight(Double weight) {
        if (weight <= 0) {
            throw new IllegalArgumentException("Weight must be positive");
        }
    }
}
