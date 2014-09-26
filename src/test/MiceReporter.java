package test;

//MiceReporter.java
//Andrew Davison, August 2012, ad@fivedots.coe.psu.ac.th

/*  List details for all the detected mouse controllers
 Uses JInput (http://java.net/projects/jinput)

 A Jinput mouse includes devices such as touch pads, trackballs, and fingersticks,
 not just mice.
 */


import net.java.games.input.*;
import net.java.games.input.Component.Identifier;
import net.java.games.input.Controller.Type;


public class MiceReporter
{

	public MiceReporter()
	{
		ControllerEnvironment ce = ControllerEnvironment.getDefaultEnvironment();
		Controller[] ca = ce.getControllers();
		Controller.Type type;
		for (int i = 0; i < ca.length; i++) {
			type = ca[i].getType();
			if (type == Controller.Type.MOUSE) {
				System.out.println("\nID " + i + "; name " + ca[i].getName());
				reportMouse((Mouse)ca[i]);
			}
		}
	}  // end of MiceReporter()



	private void reportMouse(Mouse mouseCtrl)
	/* Display info on three buttons, (x,y) movement, wheel rotation.
  JInput can detect upto 5 buttons; see the Mouse class documentation.
	 */
	{
		System.out.println("LEFT: " + getComponentInfo( mouseCtrl.getLeft()));
		System.out.println("MIDDLE: " + getComponentInfo( mouseCtrl.getMiddle()));
		System.out.println("RIGHT: " + getComponentInfo( mouseCtrl.getRight()));

		System.out.println("X: " + getComponentInfo( mouseCtrl.getX()));
		System.out.println("Y: " + getComponentInfo( mouseCtrl.getY()));
		System.out.println("Wheel: " + getComponentInfo( mouseCtrl.getWheel()));
	}  // end of reportMouse()


	private String getComponentInfo(Component c)
	/* A component might be a button, slider, or dial.

 A component's name and identifier usually seem to be identical.

 The Dead zone is the amount that data can vary before considered a significant 
 change in value; typical value is 0.
	 */
	{  
		if (c == null)
			return "none";
		else
			return (c.getName() + " - " + c.getIdentifier() +
					" - " + (c.isRelative() ? "relative" : "absolute") +
					" - " + (c.isAnalog() ? "analog" : "digital") +
					" - " + c.getDeadZone() );
	}  // end of getComponentInfo()


	// ----------------------------------------------------------------


	public static void main(String[] args)
	{  new MiceReporter(); }

}  // end of MiceReporter class
