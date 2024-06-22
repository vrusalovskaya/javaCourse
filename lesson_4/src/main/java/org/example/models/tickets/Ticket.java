package org.example.models.tickets;

import org.example.AnnotationProcessor;
import org.example.Printable;
import org.example.annotations.NullableWarning;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;

public class Ticket implements Printable {
    private final String id;
    private final long creationTime;
    private TicketPrice price;
    //according to my implementation, the id is autogenerated, thus, the presence of the
    //concert hall definition is checked
    @NullableWarning
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
        Ticket ticket = new Ticket();
        AnnotationProcessor.checkNullableWarning(ticket);
        return ticket;
    }

    public static Ticket createLimited(TicketPrice price, String concertHall, Integer eventCode, Long time) throws IllegalArgumentException {

        validateConcertHall(concertHall);
        validateEventCode(eventCode);

        Ticket ticket = new Ticket(price, concertHall, eventCode, time);
        AnnotationProcessor.checkNullableWarning(ticket);
        return ticket;
    }

    public static Ticket create(
            TicketPrice price,
            String concertHall,
            Integer eventCode,
            Long time,
            Boolean isPromo,
            StadiumSector sector,
            Double allowedBackpackWeight) throws IllegalArgumentException {

        validateConcertHall(concertHall);
        validateEventCode(eventCode);
        validateWeight(allowedBackpackWeight);

        Ticket ticket = new Ticket(price, concertHall, eventCode, time, isPromo, sector, allowedBackpackWeight);

        AnnotationProcessor.checkNullableWarning(ticket);
        return ticket;
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

    public void setTime(Long time) {
        this.time = time;
    }

    public Optional<Boolean> isPromo() {
        return get(isPromo);
    }

    public Optional<StadiumSector> getSector() {
        return get(sector);
    }

    public void setSector(StadiumSector sector) {
        this.sector = sector;
    }

    public Optional<Double> getAllowedBackpackWeight() {
        return get(allowedBackpackWeight);
    }

    @Override
    public void print() {
        System.out.println("Ticket ID: " + this.getId());
        System.out.println("Creation time: " + covertToHumanReadableDate(this.getCreationTime()));

        if (this.getPrice().isPresent() && this.getConcertHall().isPresent() && this.getEventCode().isPresent() && this.getTime().isPresent()) {
            System.out.println("Price: " + this.getPrice().get());
            System.out.println("Concert hall: " + this.getConcertHall().get());
            System.out.println("Event code: " + this.getEventCode().get());
            System.out.println("Time: " + covertToHumanReadableDate(this.getTime().get()));
        }

        if (this.isPromo().isPresent() && this.getSector().isPresent() && this.getAllowedBackpackWeight().isPresent()) {
            System.out.println("Promo ticket: " + this.isPromo().get());
            System.out.println("Stadium sector: " + this.getSector().get());
            System.out.println("Allowed backpack weight: " + formatWeight(this.getAllowedBackpackWeight().get()));
        }

        System.out.println();
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) return false;
        if (object == this) return true;
        if (!(object instanceof Ticket)) return false;
        return ((Ticket) object).getId().equals(getId());
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id='" + id + '\'' +
                ", creationTime=" + creationTime +
                ", price=" + price +
                ", concertHall='" + concertHall + '\'' +
                ", eventCode=" + eventCode +
                ", time=" + time +
                ", isPromo=" + isPromo +
                ", sector=" + sector +
                ", allowedBackpackWeight=" + allowedBackpackWeight +
                '}';
    }

    public void share(String phone) {
        System.out.println("Ticket with ID " + this.getId() + " is shared by phone: " + phone);
    }

    public void share(String phone, String email) {
        System.out.println("Ticket with ID " + this.getId() + " is shared by phone: " + phone + " and by email: " + email);
    }

    private <T> Optional<T> get(T value) {
        return value == null ? Optional.empty() : Optional.of(value);
    }

    private String generateId() {
        String digits = String.valueOf(1 + (int) (Math.random() * ((999 - 1) + 1)));
        Character character = (char) (65 + (int) (Math.random() * ((90 - 65) + 1)));
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

    private static String covertToHumanReadableDate(long unixTime) {
        Instant instant = Instant.ofEpochSecond(unixTime);
        ZonedDateTime dateTime = instant.atZone(ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }

    private static String formatWeight(double weight) {
        int kilograms = (int) weight;
        int grams = (int) ((weight - kilograms) * 1000);
        return kilograms + " kg " + grams + " g";
    }
}