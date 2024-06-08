package org.example;

import java.time.Instant;
import java.util.Optional;

public class Ticket {
    private final String id;
    private final long creationTime;
    // Main motivation to use Optional is to make sure that Empty and Limited ticket
    // would be valid from business logic validation perspective,
    // meaning that Empty or Limited ticket will not have invalid event code, etc.
    // Need feedback on that.
    private Optional<TicketPrice> price = Optional.empty();
    private Optional<String> concertHall = Optional.empty();
    private Optional<Integer> eventCode = Optional.empty();
    private Optional<Long> time = Optional.empty();
    private Optional<Boolean> isPromo = Optional.empty();
    private Optional<StadiumSector> sector = Optional.empty();
    private Optional<Double> allowedBackpackWeight = Optional.empty();

    private Ticket() {
        this.id = generateId();
        this.creationTime = getCurrentUnixTime();
    }

    private Ticket(TicketPrice price, String concertHall, Integer eventCode, Long time) {
        this();
        this.price = Optional.of(price);
        this.concertHall = Optional.of(concertHall.trim());
        this.eventCode = Optional.of(eventCode);
        this.time = Optional.of(time);
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
        this.isPromo = Optional.of(isPromo);
        this.sector = Optional.of(sector);
        this.allowedBackpackWeight = Optional.of(allowedBackpackWeight);
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
        return this.id;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public Optional<TicketPrice> getPrice() {
        return price;
    }

    public Optional<String> getConcertHall() {
        return concertHall;
    }

    public Optional<Integer> getEventCode() {
        return eventCode;
    }

    public Optional<Long> getTime() {
        return time;
    }

    public Optional<Boolean> isPromo() {
        return isPromo;
    }

    public Optional<StadiumSector> getSector() {
        return sector;
    }

    public Optional<Double> getAllowedBackpackWeight() {
        return allowedBackpackWeight;
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
