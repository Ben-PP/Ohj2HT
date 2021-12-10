package Kerho.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import Kerho.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2021.12.10 15:21:59 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class PeluriTest {



  // Generated by ComTest BEGIN
  /** testPeluri53 */
  @Test
  public void testPeluri53() {    // Peluri: 53
    Peluri ben1 = new Peluri("Ben Peluri", "Ben_P", "1000", "0501231234"); 
    ben1.rekisteroi(); 
    int n1 = ben1.getPeluriId(); 
    assertEquals("From: Peluri line: 57", n1+"|Ben Peluri|Ben_P|1000|0501231234", ben1.toString()); 
    Peluri ben2 = new Peluri("Ben Pelaaja", "Ben!P2", "", ""); 
    ben2.rekisteroi(); 
    int n2 = ben2.getPeluriId(); 
    assertEquals("From: Peluri line: 62", n2+"|Ben Pelaaja|Ben!P2|0|", ben2.toString()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testPeluri77 */
  @Test
  public void testPeluri77() {    // Peluri: 77
    Peluri ben1 = new Peluri("1|Ben Peluri|Ben_P|1000|0501231234"); 
    String s1 = ben1.toString(); 
    assertEquals("From: Peluri line: 80", true, s1.equals("1|Ben Peluri|Ben_P|1000|0501231234")); 
    ben1 = new Peluri(s1); 
    assertEquals("From: Peluri line: 82", true, ben1.toString().equals(s1)); 
    Peluri ben2 = new Peluri("5|Ben Pelaaja|Ben!P2|0|"); 
    String s2 = ben2.toString(); 
    assertEquals("From: Peluri line: 86", true, s2.equals("5|Ben Pelaaja|Ben!P2|0|")); 
    ben2 = new Peluri(s2); 
    assertEquals("From: Peluri line: 88", true, ben2.toString().equals(s2)); 
    Peluri ben3 = new Peluri("3|Ben Pelaaja|Ben!P2|0|"); 
    String s3 = ben3.toString(); 
    assertEquals("From: Peluri line: 92", true, s3.equals("3|Ben Pelaaja|Ben!P2|0|")); 
    ben3 = new Peluri(s1); 
    assertEquals("From: Peluri line: 94", true, ben3.toString().equals(s1)); 
    Peluri ben4 = new Peluri(); 
    ben4.rekisteroi(); 
    int n1 = ben2.getPeluriId(); 
    int n2 = ben4.getPeluriId(); 
    assertEquals("From: Peluri line: 100", n1+1, n2); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testRekisteroi151 */
  @Test
  public void testRekisteroi151() {    // Peluri: 151
    Peluri ben1 = new Peluri(); 
    assertEquals("From: Peluri line: 153", 0, ben1.getPeluriId()); 
    ben1.rekisteroi(); 
    Peluri ben2 = new Peluri(); 
    ben2.rekisteroi(); 
    int n1 = ben1.getPeluriId(); 
    int n2 = ben2.getPeluriId(); 
    assertEquals("From: Peluri line: 159", n2-1, n1); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testMuokkaaTietoja173 */
  @Test
  public void testMuokkaaTietoja173() {    // Peluri: 173
    Peluri ben1 = new Peluri("1|Ben Peluri|Ben_P|1000|0501231234"); 
    assertEquals("From: Peluri line: 175", "1|Ben Peluri|Ben_P|1000|0501231234", ben1.toString()); 
    String[] tiedot = { "Ben Pelurimuutettu", "Ben_Muutettu", "1200", "0506664444"} ; 
    ben1.muokkaaTietoja(tiedot); 
    assertEquals("From: Peluri line: 179", "1|Ben Pelurimuutettu|Ben_Muutettu|1200|0506664444", ben1.toString()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetPeluriId194 */
  @Test
  public void testGetPeluriId194() {    // Peluri: 194
    Peluri ben1 = new Peluri(); 
    ben1.rekisteroi(); 
    Peluri ben2 = new Peluri(); 
    ben2.rekisteroi(); 
    int n1 = ben1.getPeluriId(); 
    int n2 = ben2.getPeluriId(); 
    assertEquals("From: Peluri line: 201", n2-1, n1);
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetNimi213 */
  @Test
  public void testGetNimi213() {    // Peluri: 213
    Peluri ben1 = new Peluri("1|Ben Peluri|Ben_P|1000|0501231234"); 
    assertEquals("From: Peluri line: 215", true, ben1.getNimi().equals("Ben Peluri")); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetPNimi227 */
  @Test
  public void testGetPNimi227() {    // Peluri: 227
    Peluri ben1 = new Peluri("1|Ben Peluri|Ben_P|1000|0501231234"); 
    assertEquals("From: Peluri line: 229", true, ben1.getPNimi().equals("Ben_P")); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetTTila241 */
  @Test
  public void testGetTTila241() {    // Peluri: 241
    Peluri ben1 = new Peluri("1|Ben Peluri|Ben_P|1000|0501231234"); 
    assertEquals("From: Peluri line: 243", 1000, ben1.getTTila()); 
    Peluri ben2 = new Peluri("1|Ben Peluri|Ben_P||0501231234"); 
    assertEquals("From: Peluri line: 246", 0, ben2.getTTila()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetPuh258 */
  @Test
  public void testGetPuh258() {    // Peluri: 258
    Peluri ben1 = new Peluri("1|Ben Peluri|Ben_P|1000|0501231234"); 
    assertEquals("From: Peluri line: 260", true, ben1.getPuh().equals("0501231234")); 
    Peluri ben2 = new Peluri("1|Ben Peluri|Ben_P||"); 
    assertEquals("From: Peluri line: 263", true, ben2.getPuh().equals("")); 
  } // Generated by ComTest END
}