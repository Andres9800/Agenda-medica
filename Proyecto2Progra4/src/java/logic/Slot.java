package logic;
    
public class Slot {
    int idSlots;
    int startHour;
    int endHour;
    int day; 
    Medico medico;

    public Slot(int idSlots, int startHour, int endHour, int day, Medico medico) {
        this.idSlots = idSlots;
        this.startHour = startHour;
        this.endHour = endHour;
        this.day = day;
        this.medico = medico;
    }

    public Slot(int idSlots, int startHour, int endHour, int day) {
        this.idSlots = idSlots;
        this.startHour = startHour;
        this.endHour = endHour;
        this.day = day;
    }

    public Slot(int startHour, int endHour, int day) {
        this.startHour = startHour;
        this.endHour = endHour;
        this.day = day;
    }

    public Slot() {
    }
    
    

    public int getIdSlots() {
        return idSlots;
    }

    public void setIdSlots(int idSlots) {
        this.idSlots = idSlots;
    }

    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public int getEndHour() {
        return endHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    @Override
    public String toString() {
        return "Slot{" + "idSlots=" + idSlots + ", startHour=" + startHour + ", endHour=" + endHour + ", day=" + day + ", medico=" + medico + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.idSlots;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Slot other = (Slot) obj;
        return this.idSlots == other.idSlots;
    }
    
    
 
}
