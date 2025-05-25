package tp1.logic.lemmingRoles;

import java.util.Arrays;
import java.util.List;

import  tp1.exceptions.RoleParseException;
import tp1.view.Messages;





public class LemmingRoleFactory {
	
	private static final List<LemmingRole> availableRole = Arrays.asList(
			new DownCaverRole(),
		
			new ParachuterRole(),
				new WalkerRole()
		
			
	);
	
	

	public static LemmingRole parse(String input) throws RoleParseException{
        for (LemmingRole role : availableRole) {
            LemmingRole parsedRole = role.parse(input);
            if (parsedRole != null) {
                return parsedRole;
            }
        }
        throw new RoleParseException();
     
    }
	public static String getHelpRole() {
		String help = "";
		for(LemmingRole lr : availableRole) {
			help += "\n" + Messages.TAB+Messages.TAB+ lr.getHelp();
		}
		return help;
	}
	
	
	
}
