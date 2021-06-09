package airbnb;

public class MileageRestored extends AbstractEvent {

    private Long memId;
    private Long mileage;
    private String status;

    public MileageRestored(){
        super();
    }

    public Long getMemId() {
        return memId;
    }

    public void setMemId(Long memId) {
        this.memId = memId;
    }
    public Long getMileage() {
        return mileage;
    }

    public void setMileage(Long mileage) {
        this.mileage = mileage;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
