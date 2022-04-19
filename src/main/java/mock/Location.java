package mock;

/** Represents the location of a line, consisting of its source name and line number */
interface Location {
  String getSourceName(); // can be a file path or another name, only for diagnostic purpose

  int getLineNumber(); // counting from 1
}
