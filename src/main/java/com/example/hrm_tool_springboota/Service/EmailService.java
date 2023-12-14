package com.example.hrm_tool_springboota.Service;



import com.example.hrm_tool_springboota.Modal.Email;
import com.example.hrm_tool_springboota.Modal.LeaveApplication;
import com.example.hrm_tool_springboota.Repository.EmployeeRepository;
import com.example.hrm_tool_springboota.Repository.LeaveRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private EmployeeRepository employeeRepository;
    private LeaveRepository leaveRepository;
@Autowired
    public EmailService(JavaMailSender mailSender, EmployeeRepository employeeRepository, LeaveRepository leaveRepository) {
        this.mailSender = mailSender;
    this.employeeRepository = employeeRepository;
    this.leaveRepository = leaveRepository;
}



    public void sendEmail(Email email) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        try {
            helper.setFrom("muhammedshadeel571@gmail.com");
            helper.setTo(email.getTo());
            helper.setSubject(email.getSubject());
            helper.setText(email.getBody(), true);

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            // Handle messaging exception
            throw e;
        } catch (Exception e) {
            // Handle other exceptions
            // For example, you may log the error or display a custom error message
            e.printStackTrace();
        }
    }

    public void leavemail(LeaveApplication leaveApplication,Email email) throws MessagingException {


        try {


            Long id = leaveApplication.getId(); // Get the ID from the LeaveApplication

                LeaveApplication leaveApplication1 = leaveRepository.findById(id).orElse(null);
                String subject = "Leave Application - Approval Request for " + leaveApplication.getLeave_reason();
                StringBuilder body = new StringBuilder();
            body.append("Dear [HR Manager's Name],\n" +
                    "\n" +
                    "I trust this email finds you well. I am writing to formally request approval for my leave of absence, which is detailed below:\n" +
                    "\n" +
                    "Leave Details:\n" +
                    "\n" +
                    "    Employee Name: "+leaveApplication.getFull_name() +"\n" +
                    "    Employee ID: "+leaveApplication.getFull_name() +"\n" +
                    "    Department: "+leaveApplication.getDepartment()+"\n" +
                    "    Job Title: "+leaveApplication.getJob_title()+"\n" +
                    "    Leave Type: "+leaveApplication.getLeave_type()+"\n" +
                    "    Start Date: "+leaveApplication.getStart_date()+"\n" +
                    "    End Date: "+leaveApplication.getEnd_date()+"\n" +
                    "    Total Days: "+leaveApplication.getTotal_days()+"\n" +
                    "    Leave Reason: "+leaveApplication.getLeave_reason()+"\n" +
                    "    Alternative Contact: "+leaveApplication.getAlternative_contact()+"\n" +
                    "    Emergency Contact: "+leaveApplication.getDepartment()+"\n" +
                    "    Medical Certificate Attached: [Yes/No]\n" +
                    "\n" +
                    "I have already informed my manager, [Manager's Name], and attached the necessary details for your reference.\n" +
                    "\n" +
                    "I kindly request your approval for this leave. If you require any additional information or documentation, please let me know, and I will be happy to provide it.\n" +
                    "\n" +
                    "Moreover, I want to assure you that I have planned for a smooth handover of my responsibilities. I have delegated my tasks to [Colleague's Name], who will be able to address any urgent matters in my absence.\n" +
                    "\n" +
                    "Thank you for your prompt attention to this matter. I appreciate your assistance.\n" +
                    "\n" +
                    "Best Regards,\n" +
                    "\n" +
                            leaveApplication.getFull_name()+"\n"  +
                    leaveApplication.getJob_title()+"\n"  +

                    leaveApplication.getPhone()+"\n"  );
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom("muhammedshadeel571@gmail.com");
            helper.setTo(email.getTo()); // Replace with actual recipient email address
            helper.setSubject(subject);
            helper.setText(body.toString());

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            // Handle messaging exception
            throw e;
        } catch (Exception e) {
            // Handle other exceptions
            // For example, you may log the error or display a custom error message
            e.printStackTrace();
        }
    }

    public void getDisapprovalMail(LeaveApplication leaveApplication) throws MessagingException {
        String subject="Leave Application Status By HR";
        StringBuilder body=new StringBuilder();
        Long id = leaveApplication.getId(); // Get the ID from the LeaveApplication

        LeaveApplication leaveApplication1 = leaveRepository.findById(id).orElse(null);
        body.append("Dear " +leaveApplication1.getFull_name()+",\n" +
                "\n" +
                "I hope this message finds you well. I would like to acknowledge receipt of your leave application dated requesting leave from"+ leaveApplication1.getStart_date()+"  to "+ leaveApplication1.getEnd_date()+".\n" +
                "\n" +
                "After careful consideration and a thorough review of our current workload and operational requirements, I regret to inform you that your leave request has been disapproved.\n" +
                "\n" +
                "I understand that taking time off is important for personal reasons, and I appreciate your understanding of the situation. Please be assured that we value your contributions to the team and recognize the significance of maintaining a healthy work-life balance.\n" +
                "\n" +
                "If you have any questions or concerns regarding this decision, please feel free to reach out to me directly. I am more than happy to discuss the matter further and explore possible alternatives.\n" +
                "\n" +
                "Thank you for your understanding and cooperation.\n" +
                "\n" +
                "Best regards,\n" +
                "\n" );


        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("muhammedshadeel571@gmail.com");
        String recipientEmail = leaveApplication1.getEmail();
        System.out.println("Recipient Email: " + recipientEmail);
        helper.setSubject(subject);
        helper.setText(body.toString());

    }

    public void getApprovalMail(LeaveApplication leaveApplication) throws MessagingException {
        String subject="Leave Application Status By HR";
        StringBuilder body=new StringBuilder();
        Long id = leaveApplication.getId(); // Get the ID from the LeaveApplication

        LeaveApplication leaveApplication1 = leaveRepository.findById(id).orElse(null);
        body.append("Dear " +leaveApplication1.getFull_name()+",\n" +
                "\n" +
                "I hope this message finds you well. I would like to acknowledge receipt of your leave application dated requesting leave from"+ leaveApplication1.getStart_date()+"  to "+ leaveApplication1.getEnd_date()+".\n" +
                "\n" +
                "After careful consideration and a thorough review of our current workload and operational requirements, I inform you that your leave request has been Approved.\n" +
                "\n" +
                "I understand that taking time off is important for personal reasons, and I appreciate your understanding of the situation. Please be assured that we value your contributions to the team and recognize the significance of maintaining a healthy work-life balance.\n" +
                "\n" +
                "If you have any questions or concerns regarding this decision, please feel free to reach out to me directly. I am more than happy to discuss the matter further and explore possible alternatives.\n" +
                "\n" +
                "Thank you for your understanding and cooperation.\n" +
                "\n" +
                "Best regards,\n" +
                "\n" );


        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("muhammedshadeel571@gmail.com");
        String recipientEmail = leaveApplication1.getEmail();
        System.out.println("Recipient Email: " + recipientEmail);
        helper.setTo(recipientEmail);
        helper.setSubject(subject);
        helper.setText(body.toString());

    }
    }
