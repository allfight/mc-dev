package net.minecraft.server;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CommandHelp extends CommandAbstract {

    public CommandHelp() {}

    public String b() {
        return "help";
    }

    public String a(ICommandListener icommandlistener) {
        return icommandlistener.a("commands.help.usage", new Object[0]);
    }

    public List a() {
        return Arrays.asList(new String[] { "?"});
    }

    public void b(ICommandListener icommandlistener, String[] astring) {
        List list = this.d(icommandlistener);
        byte b0 = 7;
        int i = list.size() / b0;
        boolean flag = false;

        ICommand icommand;
        int j;

        try {
            j = astring.length == 0 ? 0 : a(icommandlistener, astring[0], 1, i + 1) - 1;
        } catch (ExceptionInvalidNumber exceptioninvalidnumber) {
            Map map = this.c();

            icommand = (ICommand) map.get(astring[0]);
            if (icommand != null) {
                throw new ExceptionUsage(icommand.a(icommandlistener), new Object[0]);
            }

            throw new ExceptionUnknownCommand();
        }

        int k = Math.min((j + 1) * b0, list.size());

        icommandlistener.sendMessage("\u00A72" + icommandlistener.a("commands.help.header", new Object[] { Integer.valueOf(j + 1), Integer.valueOf(i + 1)}));

        for (int l = j * b0; l < k; ++l) {
            icommand = (ICommand) list.get(l);
            icommandlistener.sendMessage(icommand.a(icommandlistener));
        }

        if (j == 0 && icommandlistener instanceof EntityHuman) {
            icommandlistener.sendMessage("\u00A7a" + icommandlistener.a("commands.help.footer", new Object[0]));
        }
    }

    protected List d(ICommandListener icommandlistener) {
        List list = MinecraftServer.getServer().getCommandHandler().a(icommandlistener);

        Collections.sort(list);
        return list;
    }

    protected Map c() {
        return MinecraftServer.getServer().getCommandHandler().a();
    }
}
