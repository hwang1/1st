package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;

public class ArgumentParserTest {
    
    @Test
    public void testObjectIntValue() 
	{
        ArgumentParser ap = new ArgumentParser();
        ArgumentParser.ArgumentObject ao = new ArgumentParser.ArgumentObject();
        ao.setIntValue(10);
        int t = ao.getIntValue();
        assertEquals(t, 10);
    }
    
    @Test
    public void testObjectStringEmpty()
	{
        ArgumentParser ap = new ArgumentParser();
        ArgumentParser.ArgumentObject ao = new ArgumentParser.ArgumentObject();
        String s = ao.getStringValue();
        assertEquals("", s);
    }
    
    @Test
    public void testObjectDescriptionEmpty()
    {
        ArgumentParser ap = new ArgumentParser();
        ArgumentParser.ArgumentObject ao = new ArgumentParser.ArgumentObject();
        assertEquals("", ao.getDescriptionValue());
    }
    @Test
    public void testGetDescriptionValue()
    {
        ArgumentParser ap = new ArgumentParser();
		ap.addArguments("testString", "It's a string thing", "String");
        assertEquals("It's a string thing", ap.getDescriptionValue("testString"));
    } 
    
    @Test
    public void testSetDescriptionValue()
    {
        ArgumentParser ap = new ArgumentParser();
        ArgumentParser.ArgumentObject ao = new ArgumentParser.ArgumentObject();
        String desc = new String("stuff");
        ao.setDescriptionValue(desc);
        assertEquals("stuff", ao.getDescriptionValue());
    }
    
    @Test
    public void testGetFloatValue()
    {
        ArgumentParser ap = new ArgumentParser();
        ArgumentParser.ArgumentObject ao = new ArgumentParser.ArgumentObject();
        assertEquals(0, ao.getFloatValue(), 0.01);
    }
    
    @Test
    public void testSetFloatValue()
    {
        ArgumentParser ap = new ArgumentParser();
        ArgumentParser.ArgumentObject ao = new ArgumentParser.ArgumentObject();
        float flt = 1.2f;
        ao.setFloatValue(flt);
        assertEquals(1.2, ao.getFloatValue(), 0.01);
    }
    
    @Test
    public void testSetStringValue()
    {
        ArgumentParser ap = new ArgumentParser();
        ArgumentParser.ArgumentObject ao = new ArgumentParser.ArgumentObject();
        String s = new String("stuff");
        ao.setStringValue(s);
        assertEquals("stuff", ao.getStringValue());
    }
    
    @Test
    public void testGetBooleanValue()
    {
        ArgumentParser ap = new ArgumentParser();
        ArgumentParser.ArgumentObject ao = new ArgumentParser.ArgumentObject();
        assertEquals(false, ao.getBooleanValue());
    }
    
    @Test
    public void testSetBooleanValue()
    {
        ArgumentParser ap = new ArgumentParser();
        ArgumentParser.ArgumentObject ao = new ArgumentParser.ArgumentObject();
        Boolean b = true;
        ao.setBooleanValue(b);
        assertEquals(true, ao.getBooleanValue());
    }
    
        @Test
    public void testGetDataType()
    {
        ArgumentParser ap = new ArgumentParser();
        ArgumentParser.ArgumentObject ao = new ArgumentParser.ArgumentObject();
        assertEquals("", ao.getDataType());
    }
	
    @Test
    public void testAddArgumentsNoDescription()
    {
        ArgumentParser ap = new ArgumentParser();
        String one = new String("Test Name 1");
        String two = new String("Test Name 2");
        String three = new String("Test Name 3");
        ap.addArguments(one, "String");
        ap.addArguments(two, "int");
        ap.addArguments(three, "boolean");
        String[] myStringArray = {"Hello","5","true"};
        ap.parse(myStringArray);
        assertEquals("Hello", ap.getStringValue(one));
        assertEquals(5, ap.getIntValue(two));
        assertEquals(true, ap.getBooleanValue(three));
    }
    
    @Test
    public void testAddArgumentsWithDescription()
    {
        ArgumentParser ap = new ArgumentParser();
        String one = new String("Test Name 1");
        String two = new String("Test Name 2");
        String three = new String("Test Name 3");
        ap.addArguments(one, "It's a string thing", "String");
        ap.addArguments(two, "It's an int!", "int");
        ap.addArguments(three, "It is a bool", "boolean");
        String[] myStringArray = {"Hello","5","true"};
        ap.parse(myStringArray);
        assertEquals("Hello", ap.getStringValue(one));
        assertEquals(5, ap.getIntValue(two));
        assertEquals(true, ap.getBooleanValue(three));
        assertEquals("It's a string thing", ap.getDescriptionValue(one));
        assertEquals("It's an int!", ap.getDescriptionValue(two));
        assertEquals("It is a bool", ap.getDescriptionValue(three));
    }
    
    @Test
    public void testParseValuesNotDashH() {
        ArgumentParser ap = new ArgumentParser();
        String one = new String("Length");
        String two = new String("Width");
        String three = new String("Height");
        String four = new String("Name");
        ap.addArguments(one, "Length of the object", "float");
        ap.addArguments(two, "Width of the object", "boolean");
        ap.addArguments(three, "Height of the object", "int");
        ap.addArguments(four, "Name of object", "String");
        String[] args = {"12.34", "false", "7", "Fred"};
        ap.parse(args);
        assertEquals(12.34, ap.getFloatValue(one), 0.01);
        assertEquals(false, ap.getBooleanValue(two));
        assertEquals(7, ap.getIntValue(three));
        assertEquals("Fred", ap.getStringValue(four));   
    }
   
    @Test
    public void testParse()
    {
        ArgumentParser ap = new ArgumentParser();
        ap.addArguments("thing", "Length of the object", "float");
        String[] inp = {"-h", "--stuff", "5"};
        ap.addOptionalArgument("stuff", "5");
        ap.parse(inp);
        assertEquals("5", ap.getStringValue("stuff"));
        
    }    
    
    @Test
    public void testSingleArgument()
    {
        ArgumentParser ap = new ArgumentParser();
        ap.addArguments("thing", "Length of the object", "float");
        ap.addOptionalArgument("stuff");
        String[] inp = {"-h", "--stuff", "5"};
        ap.parse(inp);
        assertEquals("5", ap.getStringValue("stuff"));
        
    }
    
    @Test
    public void testBooleanMultipleTimesTrueAndFalse() {
        ArgumentParser ap = new ArgumentParser();
        ap.addArguments("Arg 1", "This should be true", "boolean");
        ap.addArguments("Arg 2", "This should be false", "boolean");
        ap.addArguments("Arg 3", "This should be true", "boolean");
        String[] args = {"true", "false", "true"};
        ap.parse(args);
        assertEquals(true, ap.getBooleanValue("Arg 1"));
        assertEquals(false, ap.getBooleanValue("Arg 2"));
        assertEquals(true, ap.getBooleanValue("Arg 3"));  
    }
}
