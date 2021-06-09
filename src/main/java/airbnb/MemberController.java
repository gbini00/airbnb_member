package airbnb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

 @RestController
 public class MemberController {

    @Autowired MemberRepository memberRepository;

    @RequestMapping(value = "/members/chkMileage",
                    method = RequestMethod.GET,
                    produces = "application/json;charset=UTF-8")

    public long chkMileage(HttpServletRequest request, HttpServletResponse response) throws Exception {
            System.out.println("##### /members/chkMileage  called #####");

            long memId = Long.valueOf(request.getParameter("memId"));
            System.out.println("######################## chkMileage memId : " + memId);

            Optional<Member> res = memberRepository.findById(memId);
            Member member = res.get(); // 조회한 ROOM 데이터
            System.out.println("######################## chkMileage member.getMileage() : " + member.getMileage());

            return member.getMileage();
    }



 }
