/**
 * This function is executed when this Op Mode is selected from the Driver Station.
 */
function runOpMode() {
  linearOpMode.waitForStart();
  if (linearOpMode.opModeIsActive()) {
    while (linearOpMode.opModeIsActive()) {
      telemetry.addNumericData('Distance (cm)', sensorColorRangeAsREVColorRangeSensor.getDistance("CM"));
      telemetry.update();
    }
  }
}
