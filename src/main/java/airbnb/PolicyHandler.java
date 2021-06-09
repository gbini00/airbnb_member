package airbnb;

import airbnb.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class PolicyHandler{
    @Autowired MemberRepository memberRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverReviewCreated_AddMileage(@Payload ReviewCreated reviewCreated){

        if(reviewCreated.isMe()){
            System.out.println("\n\n##### listener AddMileage : " + reviewCreated.toJson() + "\n\n");

            long memId = reviewCreated.getMemId();
            long mileage = reviewCreated.getMileage();
            updateMileage(memId, "addMileage", +mileage);
        }
    }
	
    private void updateMileage(long memId, String ststus, long mileage)     {
        Optional<Member> res = memberRepository.findById(memId);
        Member member = res.get();

        System.out.println("mileage : " + member.getMileage());

        member.setMileage(member.getMileage() + mileage);
        member.setStatus(ststus);

        memberRepository.save(member);

    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}
