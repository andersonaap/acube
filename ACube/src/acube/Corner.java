package acube;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

public enum Corner {
  UFR, URB, UBL, ULF, DRF, DFL, DLB, DBR;
  public static final int size = values().length;
  private static final Corner[] values = values();
  public static final List<String> names;
  private static final List<String> canonicalNames;
  public static final EnumSet<Corner> valueSet = EnumSet.allOf(Corner.class);
  static {
    final List<String> list = new ArrayList<String>();
    for (final Corner corner : values)
      list.add(corner.name());
    names = Collections.unmodifiableList(list);
    final List<String> canonicalList = new ArrayList<String>();
    for (final Corner corner : values)
      canonicalList.add(Tools.sortCharacters(corner.name()));
    canonicalNames = Collections.unmodifiableList(canonicalList);
  }

  public static Corner value(final int ordinal) {
    return values[ordinal];
  }

  public static Corner corner(final String name) {
    return value(index(name));
  }

  public static boolean exists(final String name) {
    return index(name) >= 0;
  }

  public static int[] ordinals(final Corner[] values) {
    final int[] a = new int[values.length];
    for (int i = 0; i < a.length; i++)
      a[i] = values[i] == null ? -1 : values[i].ordinal();
    return a;
  }

  public static int twist(final String name) {
    return names.get(index(name)).indexOf(name.charAt(0));
  }

  public static int index(final String name) {
    final String sortedName = Tools.sortCharacters(name);
    for (int i = 0; i < names.size(); i++)
      if (canonicalNames.get(i).equals(sortedName))
        return i;
    return -1;
  }

  public static String name(final Corner corner, final int twist) {
    if (corner == null)
      return twist < 0 ? "@?" : twist == 1 ? "+?" : twist == 2 ? "-?" : "?";
    return twist < 0 ? "@" + corner.name(0) : corner.name(twist);
  }

  public String name(final int twist) {
    return Tools.rotate(names.get(ordinal()), twist);
  }

  @Override
  public String toString() {
    return names.get(ordinal());
  }
}
