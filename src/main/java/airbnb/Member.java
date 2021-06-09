package airbnb;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.Date;

@Entity
@Table(name="Member_table")
public class Member {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long memId;
    private Long mileage;
    private String status;

    @PostPersist
    public void onPostPersist(){
        MemberJoined memberJoined = new MemberJoined();
        BeanUtils.copyProperties(this, memberJoined);
        memberJoined.publishAfterCommit();
    }

    @PostUpdate
    public void onPostUpdate(){
        if(this.getStatus().equals("modifyMember")) {
            MemberModified memberModified = new MemberModified();
            BeanUtils.copyProperties(this, memberModified);
            memberModified.publishAfterCommit();
        }

        if(this.getStatus().equals("deleteMember")) {
            MemberDeleted memberDeleted = new MemberDeleted();
            BeanUtils.copyProperties(this, memberDeleted);
            memberDeleted.publishAfterCommit();
        }

        if(this.getStatus().equals("useMileage")) {
            MileageUsed mileageUsed = new MileageUsed();
            BeanUtils.copyProperties(this, mileageUsed);
            mileageUsed.publishAfterCommit();
        }

        if(this.getStatus().equals("restoreMileage")) {
            MileageRestored mileageRestored = new MileageRestored();
            BeanUtils.copyProperties(this, mileageRestored);
            mileageRestored.publishAfterCommit();
        }

        if(this.getStatus().equals("addMileage")) {
            MileageAdded mileageAdded = new MileageAdded();
            BeanUtils.copyProperties(this, mileageAdded);
            mileageAdded.publishAfterCommit();
        }
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
