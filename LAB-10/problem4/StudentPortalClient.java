java// Problem 4: Facade Pattern
// Subsystem classes for university portal services
class CourseRegistrationService {
    public void registerCourse(String courseName) {
        System.out.println("Registering course: " + courseName);
    }
}

class FeePaymentService {
    public void payFees(double amount) {
        System.out.println("Paying fees: $" + amount);
    }
}

class ResultService {
    public void viewResults(String studentId) {
        System.out.println("Viewing results for student ID: " + studentId);
    }
}

class CertificateService {
    public void applyCertificate(String certificateType) {
        System.out.println("Applying for certificate: " + certificateType);
    }
}

// Facade that simplifies student portal access
class StudentPortalFacade {
    private CourseRegistrationService registrationService;
    private FeePaymentService feePaymentService;
    private ResultService resultService;
    private CertificateService certificateService;

    public StudentPortalFacade() {
        this.registrationService = new CourseRegistrationService();
        this.feePaymentService = new FeePaymentService();
        this.resultService = new ResultService();
        this.certificateService = new CertificateService();
    }

    public void studentPortalAccess(String studentId, String courseName, double feeAmount, String certificateType) {
        System.out.println("--- Student Portal Access ---");
        registrationService.registerCourse(courseName);
        feePaymentService.payFees(feeAmount);
        resultService.viewResults(studentId);
        certificateService.applyCertificate(certificateType);
        System.out.println("--- End of portal interaction ---");
    }
}

// Client code
public class StudentPortalClient {
    public static void main(String[] args) {
        StudentPortalFacade portal = new StudentPortalFacade();
        portal.studentPortalAccess("S12345", "Data Structures", 1500.00, "Graduation Certificate");
    }
}