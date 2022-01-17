package org.firstinspires.ftc.bruteforce.utilities;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Blinker;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

public class BruteForceRobot {

    private DcMotor backLeft;
    private DcMotor backRight;
    private DcMotor frontLeft;
    private DcMotor frontRight; 
    private DcMotor carouselMotor;
    private DcMotor rightClawMotor;
    
    private Blinker controlHub;
    private Blinker expansionHub;
    private CRServo continuousServo1;
    private CRServo continuousServo2;
    private Gyroscope imu;
    private ColorSensor sensorColorRange;
    private Servo clawServo;
    private Servo servo2;
    private Servo servo3;
    private Servo servo4;
    private TouchSensor testTouch;
    
    public BruteForceRobot(DcMotor fl, DcMotor fr, DcMotor bl, DcMotor br, DcMotor c) {
        frontLeft = fl;
        frontRight = fr;
        backLeft = bl;
        backRight = br;
        carouselMotor = c;        
    } 
    
    public BruteForceRobot(HardwareMap hardwareMap) {
        controlHub = hardwareMap.get(Blinker.class, "Control Hub");
        expansionHub = hardwareMap.get(Blinker.class, "Expansion Hub 2");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        carouselMotor = hardwareMap.get(DcMotor.class, "carouselMotor");
        rightClawMotor = hardwareMap.get(DcMotor.class, "rightClawMotor");
        continuousServo1 = hardwareMap.get(CRServo.class, "continuousServo1");
        continuousServo2 = hardwareMap.get(CRServo.class, "continuousServo2");
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        imu = hardwareMap.get(Gyroscope.class, "imu");
        sensorColorRange = hardwareMap.get(ColorSensor.class, "sensorColorRange");
        clawServo = hardwareMap.get(Servo.class, "clawServo");
        servo2 = hardwareMap.get(Servo.class, "servo2");
        servo3 = hardwareMap.get(Servo.class, "servo3");
        servo4 = hardwareMap.get(Servo.class, "servo4");
        testTouch = hardwareMap.get(TouchSensor.class, "testTouch");
    }

    public void moveForward(double n) {
        moveVertical(-n);
    }
    
    public void moveBackward(double n) {
        moveVertical(n);
    }    
    
    // If n is positive, move forward
    // If n is negative, move backward
    public void moveVertical(double n) {
        frontLeft.setPower(1 * n);
        frontRight.setPower(-1 * n);
        backLeft.setPower(1 * n);
        backRight.setPower(-1 * n);
    }
    
    public void moveLeft(double n) {
        moveHorizontal(-n);
    }
    
    public void moveRight(double n) {
        moveHorizontal(n); 
    }    
    
    // If n is positive, move left
    // If n is negative, move right
    public void moveHorizontal(double n) {
        frontLeft.setPower(-1 * n);
        frontRight.setPower(-1 * n);
        backLeft.setPower(1 * n);
        backRight.setPower(1 * n); 
    }    
    
    public void rotateRight (double n) {
        rotate(-n);
    }
    
    public void rotateLeft (double n) {
        rotate(n);
    }    
    
    public void rotate (double n) {
        frontLeft.setPower(1 * n);
        frontRight.setPower(1 * n);
        backLeft.setPower(1 * n);
        backRight.setPower(1 * n);
    }    
    
    public void rotateCarousel(double n) {
        carouselMotor.setPower(n);
    }
    
    public void stopMovement() {
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }
    
    public void stopCarousel() {
        carouselMotor.setPower(0);
    }
    
    public void stopClaw() {
        rightClawMotor.setPower(0);
    }
    
    public void robotStop() { 
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
        carouselMotor.setPower(0);
        rightClawMotor.setPower(0);
    }
    
    public void moveClaw(double o) {
        clawServo.setPosition(o);
    }
    
    public void stopCoast() {
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    //Moves the claw
    public void liftArm(double b) {
        rightClawMotor.setPower(b*.5);
        //leftClawMotor.setPower(-b*.5);
    }       
}