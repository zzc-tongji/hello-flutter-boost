import 'dart:ui' as ui;

/// Convert [p] vw to logic pixel
///
/// - Unit "vw" is defined as 1% of view port width,
///   which is similar as https://developer.mozilla.org/en-US/docs/Web/CSS/length
/// - The default unit of Dart and Flutter is "logic pixel".
double vw(double p) {
  return ui.window.physicalSize.width / ui.window.devicePixelRatio / 100 * p;
}
