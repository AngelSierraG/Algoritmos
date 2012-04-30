public class DuplicateDataException extends java.lang.Exception
{
  /**
   * When this exception is thrown, the key in the database
   * that raised the exception is returned.
   */
  protected Key key;

  public DuplicateDataException(Key k)
  { 
    key = k; 
  }

  public Key getKey()
  { 
    return key;
  }
}