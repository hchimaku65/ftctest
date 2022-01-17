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
package org.firstinspires.ftc.bruteforce.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
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
import org.firstinspires.ftc.bruteforce.utilities.BruteForceRobot;

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
@TeleOp
public class DriverMode extends LinearOpMode {

    
/*    
    // functions for the robot movements and carousel as defined
    public void robotStop() {
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
        carouselMotor.setPower(0);
        leftClawMotor.setPower(0);
        rightClawMotor.setPower(0);
    }
    //Moves Robot Forward/Backward
    public void forwardBackward(double x) {
        frontLeft.setPower(x*.7);
        frontRight.setPower(-x*.7);
        backLeft.setPower(x*.7);
        backRight.setPower(-x*.7);
    }
    //Moves Robot Right/Left
    public void rightLeft(double y) {
        frontLeft.setPower(-y);
        frontRight.setPower(-y);
        backLeft.setPower(y);
        backRight.setPower(y);
    }
    
    public void rotate(double z) {
    //Rotates the Robot   
        frontLeft.setPower(-z*.7);
        frontRight.setPower(-z*.7);
        backLeft.setPower(-z*.7);
        backRight.setPower(-z*.7);
    }
    //Moves carousel Wheel
    public void rotateCarousel(double a) {
        carouselMotor.setPower(a);
    }
    //Moves the claw
    public void clawMotor(double b) {
        rightClawMotor.setPower(b*.5);
        //leftClawMotor.setPower(-b*.5);
    }
*/

//Runs Tele-Op Mode
//Main program that has the logic to run the missions.
    @Override
    public void runOpMode() {
       
        BruteForceRobot robot = new BruteForceRobot(hardwareMap);

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        
        // run until the end of the match (driver presses STOP)
        // the variable for Right Stick X, Right Stick Y, Left Stick X, Left Stick Y are defined
        double rsy1 = 0;
        double rsx1 = 0;
        double lsx1 = 0;
        double lsy1 = 0;
        double rsy2 = 0;
        double rsx2 = 0;
        double lsx2 = 0;
        double lsy2 = 0;
        while (opModeIsActive()) {
            // the controller movements are assigned to the variables
            rsy1 = this.gamepad1.right_stick_y;
            rsx1 = this.gamepad1.right_stick_x;
            lsx1 = this.gamepad1.left_stick_x;
            lsy1 = this.gamepad1.left_stick_y;
            rsy2 = this.gamepad2.right_stick_y;
            rsx2 = this.gamepad2.right_stick_x;
            lsx2 = this.gamepad2.left_stick_x;
            lsy2 = this.gamepad2.left_stick_y;
            // The variables are assigned to the robot movement functions and the carousel
         
            if (rsy1 != 0) {
                robot.moveVertical(rsy1);
            } else if (rsx1 > 0) {
                robot.moveHorizontal(1);
            } else if (rsx1 < 0) {
                robot.moveHorizontal(-1);
            } else if (lsx1 != 0) {
                robot.rotate(lsx1);
            } else {
                robot.stopMovement();
            }
            
            
            if (gamepad1.x) {
                robot.rotateCarousel(1);
            } else {
                robot.stopCarousel();
            }
            
            if (rsy2 != 0) {
                robot.liftArm(rsy2);
            } else {
                robot.stopClaw();
            }
            
            if(gamepad2.y) {
                // move to 0 degrees.
                robot.moveClaw(0);
            } else if (gamepad2.a) {
                // move to 180 degrees.
                robot.moveClaw(1);
            }
            /*
            forwardBackward(rsy);
            rightLeft(rsx);
            rotate(lsx);
            rotateCarousel(lsy);
            */
            // telementary is added for the 4 stick values
            
            //This is a note from Pranav: Hi :)
          //Shows Values on Driver hub  
            telemetry.addData("Right Stick Y 1", rsy1);
            telemetry.addData("Right Stick X 1", rsx1);                         
            telemetry.addData("Left Stick X 1", lsx1);
            telemetry.addData("Left Stick Y 1", lsy1);
            telemetry.addData("Right Stick Y 2", rsy2);
            telemetry.addData("Right Stick X 2", rsx2);
            telemetry.addData("Left Stick X 2", lsx2);
            telemetry.addData("Left Stick Y 2", lsy2);
            telemetry.addData("Status", "Running");
            telemetry.update();
            
            
        }
    }
}
