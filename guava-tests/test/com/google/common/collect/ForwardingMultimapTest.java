/*
 * Copyright (C) 2009 The Guava Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.common.collect;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Unit test for {@link ForwardingMultimap}.
 *
 * @author Hayward Chan
 */
public class ForwardingMultimapTest extends ForwardingTestCase {

  // Package-private so subclasses can access this variable.
  Multimap<String, Boolean> forward;

  @Override public void setUp() throws Exception {
    super.setUp();
    /*
     * Class parameters must be raw, so we can't create a proxy with generic
     * type arguments. The created proxy only records calls and returns null, so
     * the type is irrelevant at runtime.
     */
    @SuppressWarnings("unchecked")
    final Multimap<String, Boolean> multimap =
        createProxyInstance(Multimap.class);
    forward = new ForwardingMultimap<String, Boolean>() {
      @Override protected Multimap<String, Boolean> delegate() {
        return multimap;
      }
    };
  }

  public void testSize() {
    int unused = forward.size();
    assertEquals("[size]", getCalls());
  }

  public void testIsEmpty() {
    boolean unused = forward.isEmpty();
    assertEquals("[isEmpty]", getCalls());
  }

  public void testContainsKey_Object() {
    boolean unused = forward.containsKey("asdf");
    assertEquals("[containsKey(Object)]", getCalls());
  }

  public void testContainsValue_Object() {
    boolean unused = forward.containsValue(false);
    assertEquals("[containsValue(Object)]", getCalls());
  }

  public void testContainsEntry_Object_Object() {
    boolean unused = forward.containsEntry("asdf", false);
    assertEquals("[containsEntry(Object,Object)]", getCalls());
  }

  public void testPut_Key_Value() {
    forward.put("asdf", true);
    assertEquals("[put(Object,Object)]", getCalls());
  }

  public void testRemove_Key_Value() {
    forward.remove("asdf", false);
    assertEquals("[remove(Object,Object)]", getCalls());
  }

  public void testPutAll_Key_Iterable() {
    forward.putAll("asfd", Collections.<Boolean>emptyList());
    assertEquals("[putAll(Object,Iterable)]", getCalls());
  }

  public void testPutAll_Multimap() {
    forward.putAll(ArrayListMultimap.<String, Boolean>create());
    assertEquals("[putAll(Multimap)]", getCalls());
  }

  public void testReplaceValues_Key_Iterable() {
    forward.replaceValues("key", Collections.<Boolean>emptyList());
    assertEquals("[replaceValues(Object,Iterable)]", getCalls());
  }

  public void testRemoveAll_Object() {
    forward.removeAll("key");
    assertEquals("[removeAll(Object)]", getCalls());
  }

  public void testClear() {
    forward.clear();
    assertEquals("[clear]", getCalls());
  }

  public void testGet_Key() {
    Collection<Boolean> unused = forward.get(null);
    assertEquals("[get(Object)]", getCalls());
  }

  public void testKeySet() {
    Set<String> unused = forward.keySet();
    assertEquals("[keySet]", getCalls());
  }

  public void testKeys() {
    Multiset<String> unused = forward.keys();
    assertEquals("[keys]", getCalls());
  }

  public void testValues() {
    Collection<Boolean> unused = forward.values();
    assertEquals("[values]", getCalls());
  }

  public void testEntries() {
    Collection<Entry<String, Boolean>> unused = forward.entries();
    assertEquals("[entries]", getCalls());
  }

  public void testAsMap() {
    Map<String, Collection<Boolean>> unused = forward.asMap();
    assertEquals("[asMap]", getCalls());
  }

  public void testEquals() {
    boolean unused = forward.equals(null);
    assertEquals("[equals(Object)]", getCalls());
  }

  public void testHashCode() {
    int unused = forward.hashCode();
    assertEquals("[hashCode]", getCalls());
  }

  public void testToString() {
    String unused = forward.toString();
    assertEquals("[toString]", getCalls());
  }
}
