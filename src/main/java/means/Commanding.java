/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package means;

import funks.Commander;
import funks.PreLoader;

/**
 *
 * @author rash4
 */
public final class Commanding implements Commander, PreLoader{
    Commanding(){this.CMDS = new SubCommand();}
    @Override public boolean execute(String command) {
        if(command == null)return false;
        final var cons = Launcher.console();
        cons.appendl(command);
        final var parts = parts(command);
        final String pt1;
        final String remainings = (parts.length > 1) ? parts[1] : null;
        final Com com;
        if(parts.length > 0){
            pt1 = parts[0].toLowerCase();
            com = this.CMDS.getCom(pt1);
            if(com == null){
                cons.appendl(">> Command not found... \n  - write 'help' to get the main commands list.");
                return false;
            }return switch(com){
                case Command cmd -> cmd.com().execute(remainings);
                case SubCommand sub -> {
                    if(remainings == null){
                        if(sub.hasDirectCom())yield sub.directCommand().com().execute("");
                        yield false;
                    }
                    yield this.checkNest(remainings, sub);
                }
            };
        }
        return false;
    }
    private boolean checkNest(String command, SubCommand next){
        final var cons = Launcher.console();
        final var parts = parts(command);
        final String pt1;
        final String remainings = (parts.length > 1) ? parts[1] : null;
        final Com com;
        if(parts.length > 0){
            pt1 = parts[0].toLowerCase();
            com = next.getCom(pt1);
            if(com == null){
                cons.appendl(">> Command not found... \n  - write 'help' to get the main commands list.");
                return false;
            }return switch(com){
                case Command cmd -> cmd.com().execute(remainings);
                case SubCommand sub -> {
                    if(remainings == null){
                        if(sub.hasDirectCom())yield sub.directCommand().com().execute("");
                        yield false;
                    }
                    yield this.checkNest(remainings, sub);
                }
            };
        }
        return false;
    }
    public boolean fullHelpList(String bleh){
        final var cons = Launcher.console();
        cons.appendl(">> Current MAIN commands -> list : ");
        this.CMDS.listHelp("   ");
        cons.appendl(">> List-End <<||");
        return true;
    }
    public boolean getHelp(String bleh){// for the sake of the method's reference
        final var cons = Launcher.console();
        cons.appendl(">> Current MAIN commands -> list : ");
        for(String como : this.CMDS.getList()){
            cons.appendl("  --" + como + ".");
        }
        cons.appendl(">> List-End <<||");
        return true;
    }
    public Commanding addCommand(Com neo, String name){
        if(neo == null || name == null || name.isBlank())return this; // fast escape.
        switch(neo){
            case SubCommand sub-> this.CMDS.setCom(name, sub);
            case Command cmd-> this.CMDS.setCom(name, cmd);
        }return this;
    }
    @Override public void preLoad() {
        // empty for now
    }
    public static String[] parts(String series){return (series == null ? "" : series).trim().split("\\s+", 2);}
    private final SubCommand CMDS;
}