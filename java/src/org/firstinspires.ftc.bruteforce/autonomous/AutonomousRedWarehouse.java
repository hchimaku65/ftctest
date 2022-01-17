/*
Copyright 2021 FIRST Tech Challenge Team FTC

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
associated documentation files (the "Software"), to deal in the Software without restriction,
including without limitation the rights to use, copy, modify, merge, publish, distribute,
sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial
portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/
package org.firstinspires.ftc.bruteforce.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.Blinker;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a PushBot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Remove a @Disabled the on the next line or two (if present) to add this opmode to the Driver Station OpMode list,
 * or add a @Disabled annotation to prevent this OpMode from being added to the Driver Station
 */
@Autonomous

public class AutonomousRedWarehouse extends LinearOpMode {
    private Blinker controlHub;
    private Blinker expansionHub;
    private DcMotor backLeft;
    private DcMotor backRight;
    private CRServo continuousServo1;
    private CRServo continuousServo2;
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private Gyroscope imu;
    private ColorSensor sensorColorRange;
    private Servo clawServo;
    private Servo servo2;
    private Servo servo3;
    private Servo servo4;
    private TouchSensor testTouch;
    private DcMotor carouselMotor;
    
    public void robotStop() {
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }
    
    public void robotForward() {
        frontLeft.setPower(1);
        frontRight.setPower(-1);
        backLeft.setPower(1);
        backRight.setPower(-1);
    }
    
    public void robotBackward() {
        frontLeft.setPower(-1);
        frontRight.setPower(1);
        backLeft.setPower(-1);
        backRight.setPower(1);   
    }
    
    public void robotRight() {
        frontLeft.setPower(1);
        frontRight.setPower(1);
        backLeft.setPower(-1);
        backRight.setPower(-1);    
    }
    
    public void robotLeft() {
        frontLeft.setPower(-1);
        frontRight.setPower(-1);
        backLeft.setPower(1);
        backRight.setPower(1); 
    }
    
    public void robotNE() {
        frontLeft.setPower(1);
        backRight.setPower(-1); 
    }
    
    public void robotNW() {
        frontRight.setPower(-1);
        backLeft.setPower(1);
    }
    
    public void robotSE() {
        frontRight.setPower(1);
        backLeft.setPower(-1);    
    }
    
    public void robotSW() {
        frontLeft.setPower(-1);
        backLeft.setPower(1);    
    }
    
    public void robotRL() {
        frontLeft.setPower(-1);
        frontRight.setPower(-1);
        backLeft.setPower(-1);
        backRight.setPower(-1);
    }
    
    public void robotRR () {
        frontLeft.setPower(1);
        frontRight.setPower(1);
        backLeft.setPower(1);
        backRight.setPower(1);
    }


    @Override
    public void runOpMode() {
        controlHub = hardwareMap.get(Blinker.class, "Control Hub");
        expansionHub = hardwareMap.get(Blinker.class, "Expansion Hub 2");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
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

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            robotForward();
            sleep(6000);
            // Duck carousel program
            robotRR();
            sleep(4000);
            robotForward();
            sleep(6000);
            robotRL();
            sleep(2000);
            robotForward();
            sleep(1000);
            // Put freight in the Alliance Shipping Hub
            robotBackward();
            sleep(1000);
            robotRR();
            sleep(2000);
            robotForward();
            sleep(6000);
            robotStop();
            telemetry.addData("Status", "Running");
            telemetry.update();

        }
    }
}
