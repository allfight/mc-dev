package net.minecraft.server;

public class BlockNote extends BlockContainer {

    public BlockNote(int i) {
        super(i, 74, Material.WOOD);
        this.a(CreativeModeTab.d);
    }

    public int a(int i) {
        return this.textureId;
    }

    public void doPhysics(World world, int i, int j, int k, int l) {
        if (l > 0) {
            boolean flag = world.isBlockIndirectlyPowered(i, j, k);
            TileEntityNote tileentitynote = (TileEntityNote) world.getTileEntity(i, j, k);

            if (tileentitynote != null && tileentitynote.b != flag) {
                if (flag) {
                    tileentitynote.play(world, i, j, k);
                }

                tileentitynote.b = flag;
            }
        }
    }

    public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman, int l, float f, float f1, float f2) {
        if (world.isStatic) {
            return true;
        } else {
            TileEntityNote tileentitynote = (TileEntityNote) world.getTileEntity(i, j, k);

            if (tileentitynote != null) {
                tileentitynote.a();
                tileentitynote.play(world, i, j, k);
            }

            return true;
        }
    }

    public void attack(World world, int i, int j, int k, EntityHuman entityhuman) {
        if (!world.isStatic) {
            TileEntityNote tileentitynote = (TileEntityNote) world.getTileEntity(i, j, k);

            if (tileentitynote != null) {
                tileentitynote.play(world, i, j, k);
            }
        }
    }

    public TileEntity a(World world) {
        return new TileEntityNote();
    }

    public void b(World world, int i, int j, int k, int l, int i1) {
        float f = (float) Math.pow(2.0D, (double) (i1 - 12) / 12.0D);
        String s = "harp";

        if (l == 1) {
            s = "bd";
        }

        if (l == 2) {
            s = "snare";
        }

        if (l == 3) {
            s = "hat";
        }

        if (l == 4) {
            s = "bassattack";
        }

        world.makeSound((double) i + 0.5D, (double) j + 0.5D, (double) k + 0.5D, "note." + s, 3.0F, f);
        world.a("note", (double) i + 0.5D, (double) j + 1.2D, (double) k + 0.5D, (double) i1 / 24.0D, 0.0D, 0.0D);
    }
}
